package ar.edu.teclab.prueba.controller;

import com.google.api.client.util.Base64;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api")
public class TicketController {

    private static String URL = "https://teclab1593636133.zendesk.com/api/v2/tickets/";

    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getComments(@PathVariable(value="id")Long id) {

        RestTemplate rt = new RestTemplate();
        String urlGetComments = URL + id + "/comments.json";
        HttpHeaders headers = createHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(urlGetComments, HttpMethod.GET, request, String.class);

        return response.getBody();
    }

    @PostMapping("/add_comment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addComment(@PathVariable(value="id")Long id) {
        RestTemplate rt = new RestTemplate();
        String urlAddComment = URL + id + ".json";
        Ticket ticket = loadTicket();
        HttpHeaders headers = createHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Ticket> request = new HttpEntity<>(ticket, headers);
        ResponseEntity<String> response = rt.exchange(urlAddComment, HttpMethod.PUT, request, String.class);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private HttpHeaders createHeaders() {
        return new HttpHeaders() {{
            String auth = "jorge.danni@teclab.edu.ar:Abril2019";
            byte[] encodeAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII"))
            );
            String authHeader = "Basic " + new String(encodeAuth);
            set("Authorization", authHeader);
        }};
    }

    private Ticket loadTicket() {
        Comment comment = new Comment("Prueba Sooft", 1L);
        Ticket ticket = new Ticket(comment);

        return ticket;
    }

    class Ticket {
        private Comment comment;

        public Ticket(Comment comment) {
            this.comment = comment;
        }

        public Comment getComment() {
            return comment;
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }
    }

    class Comment {
        private String body;
        private Long authorId;

        public Comment(String body, Long authorId) {
            this.body = body;
            this.authorId = authorId;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Long getAuthorId() {
            return authorId;
        }

        public void setAuthorId(Long authorId) {
            this.authorId = authorId;
        }
    }
}
