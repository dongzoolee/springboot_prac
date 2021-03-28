package cloud2.demo.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @Controller는 주로 View를 반환하기 위해 사용합니다.
@Controller
// 예전에 프로그래밍을 할 때에는 jsp나 html과 같은 뷰를 전달해 주었기 때문에
// @Controller를 사용해왔었지만, 최근에는 프론트엔드와 백엔드를 따로 두고,
// 백엔드에서는 REST API를 통해 json으로 데이터만 전달하기 때문에 편리성을 위해
// @RestController를 사용하게 되었다
public class hello {

    @GetMapping("/hello")
    public String showHelloModel(Model model) {
        model.addAttribute("message", "hello world");

        return "hello";
    }
}
