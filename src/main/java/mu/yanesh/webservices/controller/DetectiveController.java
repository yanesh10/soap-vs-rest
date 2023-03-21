package mu.yanesh.webservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.guides.gs_producing_web_service.DetectiveServiceRequest;
import io.spring.guides.gs_producing_web_service.DetectiveServiceResponse;
import lombok.AllArgsConstructor;
import mu.yanesh.webservices.exceptions.DetectiveNotFoundException;
import mu.yanesh.webservices.exceptions.MissingRequiredFieldException;
import mu.yanesh.webservices.models.Constants;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.services.DetectiveService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import java.util.Objects;

@Endpoint
@AllArgsConstructor
public class DetectiveController {

    private final DetectiveService detectiveService;

    private final ObjectMapper objectMapper;

    @SoapAction(value = Constants.GET_DETECTIVE)
    @ResponsePayload
    public DetectiveServiceResponse getDetective(@RequestPayload DetectiveServiceRequest request) {
        if (request.getDetective().getId() <= 0) {
            throw new MissingRequiredFieldException(Constants.ID);
        }
        DetectiveServiceResponse response = new DetectiveServiceResponse();
        Detective detective = detectiveService.getById(request.getDetective().getId()).orElseThrow(DetectiveNotFoundException::new);
        response.setDetective(objectMapper.convertValue(detective, io.spring.guides.gs_producing_web_service.Detective.class));
        return response;
    }

    @SoapAction(value = Constants.SAVE_DETECTIVE)
    @ResponsePayload
    public DetectiveServiceResponse saveDetective(@RequestPayload DetectiveServiceRequest request) {
        Objects.requireNonNull(request.getDetective());
        DetectiveServiceResponse response = new DetectiveServiceResponse();

        response.setDetective(objectMapper.convertValue(detectiveService.save(objectMapper.convertValue(request.getDetective(), Detective.class)), io.spring.guides.gs_producing_web_service.Detective.class));
        return response;
    }

    @SoapAction(value = Constants.DELETE_DETECTIVE)
    @ResponsePayload
    public DetectiveServiceResponse deleteDetective(@RequestPayload DetectiveServiceRequest request) {
        if (request.getDetective().getId() <= 0) {
            throw new MissingRequiredFieldException(Constants.ID);
        }
        detectiveService.delete(request.getDetective().getId());
        return null;
    }


}
