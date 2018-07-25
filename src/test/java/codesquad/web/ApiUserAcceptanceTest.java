package codesquad.web;

import codesquad.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import codesquad.support.AcceptanceTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiUserAcceptanceTest extends AcceptanceTest {

    private UserDto user;

    @Before
    public void setUp() throws Exception {
        user = UserDto.builder()
                .email("a@b.com")
                .name("이혁진")
                .password("gkgkgk123!")
                .confirmPassword("gkgkgk123!")
                .phone("010-1234-5678")
                .build();
    }

    @Test
    public void create() {
        ResponseEntity<Void> response = template().postForEntity("/users", user, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void create_유효하지않은_인자() {
        user.setEmail("ab"); // sample invalid argument
        ResponseEntity<Void> response = template().postForEntity("/users", user, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST); // 400
    }
}
