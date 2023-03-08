package mu.yanesh.webservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.guides.gs_producing_web_service.*;
import lombok.AllArgsConstructor;
import mu.yanesh.webservices.exceptions.NoLeadDetectiveException;
import mu.yanesh.webservices.services.CaseService;
import mu.yanesh.webservices.services.DetectiveService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@AllArgsConstructor
public class ControllerSoap {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private final CaseService caseService;
    private final DetectiveService detectiveService;
    private final ObjectMapper objectMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCasesRequest")
    @ResponsePayload
    public GetAllCasesResponse getAllCases() {
        GetAllCasesResponse response = new GetAllCasesResponse();
        response.getCase().addAll(caseService.getAll().stream()
                .map(c -> objectMapper.convertValue(c, Case.class))
                .toList());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllDetectiveForCaseRequest")
    @ResponsePayload
    public GetAllDetectiveForCaseResponse getAllDetectiveForCase(@RequestPayload GetAllDetectiveForCaseRequest request) {
        GetAllDetectiveForCaseResponse response = new GetAllDetectiveForCaseResponse();
        response.getDetectiveList().addAll(caseService.getAllDetectiveByCase(request.getCaseId())
                .stream()
                .map(d -> objectMapper.convertValue(d, Detective.class))
                .toList());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLeadDetectiveRequest")
    @ResponsePayload
    public GetLeadDetectiveResponse getLeadDetective(@RequestPayload GetLeadDetectiveRequest request) {
        GetLeadDetectiveResponse response = new GetLeadDetectiveResponse();
        response.setDetective(caseService.getLeadDetective(request.getCaseId())
                .map(d -> objectMapper.convertValue(d, Detective.class))
                .orElseThrow(NoLeadDetectiveException::new));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCaseByStatusRequest")
    @ResponsePayload
    public GetCaseByStatusResponse getCaseByStatus(@RequestPayload GetCaseByStatusRequest request) {
        GetCaseByStatusResponse response = new GetCaseByStatusResponse();
        response.getCaseList().addAll(caseService.getAllCasesByStatus(objectMapper.convertValue(request.getStatus(), mu.yanesh.webservices.models.Status.class))
                .stream()
                .map(c -> objectMapper.convertValue(c, Case.class))
                .toList());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllDetectiveRequest")
    @ResponsePayload
    public GetAllDetectiveResponse getAllDetective() {
        GetAllDetectiveResponse response = new GetAllDetectiveResponse();
        response.getDetectiveList().addAll(detectiveService.getAll()
                .stream()
                .map(d -> objectMapper.convertValue(d, Detective.class))
                .toList());
        return response;
    }
}
