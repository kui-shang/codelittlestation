package app.netlify.codelittlestation.codelittlestation;

import app.netlify.codelittlestation.codelittlestation.classes.fileIO;
import app.netlify.codelittlestation.codelittlestation.classes.log;
import app.netlify.codelittlestation.codelittlestation.classes.nt;
import app.netlify.codelittlestation.codelittlestation.classes.strs;
import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Main {
    @RequestMapping("/**")
    public String gethtml(HttpServletRequest h) throws IOException {
        String uri = h.getRequestURI();

//        String ip = h.getRemoteAddr();
        log.INFOLog(" 请求资源 "+uri);

        List<String> s = strs.fenStr(uri, '/');
        StringBuilder cp = new StringBuilder("存放代码的目录");
        for(String i:s){
            cp.append("\\").append(i);
        }
        if(!strs.check(s)){
            cp.append("\\index.html");
        }
        String codePath = cp.toString();
        try {
            return fileIO.read(codePath);
        } catch (NoSuchFileException e){
            log.INFOLog(" 请求资源 "+uri+" 时出现了404错误");
            return strs.getErrorHTML("404","什么都木有");
        } catch(IOException e){
            log.INFOLog(" 请求资源 "+uri+" 时出现了500错误");
            return strs.getErrorHTML("500","服务器错误");
        }
    }

    // 做了一个类似于端口转发的东西（暂未测试）
    @RequestMapping("/toport/{port}/**")
    public ResponseEntity<String> toport(@PathVariable("port") int port, HttpServletRequest h) throws IOException {
        StringBuffer url = h.getRequestURL();
        Cookie[] c = h.getCookies();
        String uri = h.getRequestURI();
        Map<String,Object> mp = new HashMap<>();
        mp.put("uri",uri);
        mp.put("cookie",c);
        mp.put("url",url.toString());
        Gson g = new Gson();
        String msg = g.toJson(mp);
        String b = nt.send("127.0.0.1",port,msg,true);
        return ResponseEntity.status(HttpStatus.OK).body(b);
    }
}
