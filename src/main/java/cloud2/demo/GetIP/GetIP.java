package cloud2.demo.GetIP;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetIP {

    @GetMapping("/GetIP")
    public String GetIP(){
        HashMap<String, Object> result = new HashMap<String, Object>();

        String jsonInString = "";

        try {
            // 이러한 요청을
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); //타임아웃 설정 5초
            factory.setReadTimeout(5000);//타임아웃 설정 5초

            // RestTemplate에 담아 보낼 거임
            RestTemplate restTemplate = new RestTemplate(factory);

            // 헤더도 만들어 주고
            HttpHeaders header = new HttpHeaders();
            // Http 객체에 넣어준다
            HttpEntity<?> entity = new HttpEntity<>(header);

            //요청을 보낼 주소는 다음과 같고
            String url = "https://ip.leed.at";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            // 이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
            // URL, 요청 방식, Http 객체, 담아올 형식까지
            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
            result.put("statusCode", resultMap.getStatusCodeValue()); // http status code를 내가 만든 result에 넣고
            result.put("header", resultMap.getHeaders()); // 헤더 정보도 내가 만든 result에 넣고
            result.put("body", resultMap.getBody()); // Body도 내가 만든 result에 넣고

            // JAVA Object를 JSON String으로 바꾸는데 도움을 주는 친구
            ObjectMapper mapper = new ObjectMapper();
            // Convert object to JSON string
            // jsonInString = mapper.writeValueAsString(resultMap.getBody());

            // ObjectMapper 안 쓰고 그냥 resultMap에서 바로 Body 가져와서 쓸 수도 있음
            jsonInString = "Your IP Address is "+resultMap.getBody().get("ip").toString();
            System.out.println(result.toString());

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("dfdfdfdf");
            System.out.println(e.toString());

        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println(e.toString());
        }

        return jsonInString;
    }
}
