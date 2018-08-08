package codesquad.banchan;

import codesquad.support.AcceptanceTest;
import codesquad.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiBanchanAcceptanceTest extends AcceptanceTest {

    @Test
    public void getBanchan_단어검색() {
        String keyword = "존맛";
        ResponseEntity<RestResponse> response = template().getForEntity("/banchans/search?keyword=" + keyword, RestResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isNotNull();
    }
}
