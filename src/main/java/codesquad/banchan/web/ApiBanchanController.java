package codesquad.banchan.web;

import codesquad.banchan.domain.BanchanInterface;
import codesquad.banchan.domain.BanchanRepository;
import codesquad.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/banchans")
public class ApiBanchanController {

    @Autowired
    private BanchanRepository banchanRepository;

    @GetMapping("/search")
    public ResponseEntity<RestResponse> list(@RequestParam("keyword") String keyword){
        List<BanchanInterface> banchans = banchanRepository.findFirst10ByTitleContaining(keyword);
        return ResponseEntity.ok((RestResponse.of(banchans)));
    }


}
