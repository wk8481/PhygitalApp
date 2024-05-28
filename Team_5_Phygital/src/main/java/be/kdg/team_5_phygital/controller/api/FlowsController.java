package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.FlowDto;
import be.kdg.team_5_phygital.controller.api.dto.NewFlowDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateFlowDto;
import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.repository.FlowRepository;
import be.kdg.team_5_phygital.service.FlowService;
import be.kdg.team_5_phygital.service.ProjectService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flows")
public class FlowsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FlowService flowService;
    private final ModelMapper modelMapper;

    public FlowsController(FlowService flowService, ModelMapper modelMapper) {
        this.flowService = flowService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<FlowDto> getFlow(@PathVariable("id") int flowId) {
        Flow flow = flowService.getFlow(flowId);
        if (flow == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(flow, FlowDto.class));
    }

    @GetMapping
    ResponseEntity<List<FlowDto>> getAllFlows() {
        List<Flow> allFlows = flowService.getAllFlows();
        if (allFlows.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<FlowDto> flowDtos = allFlows.stream().map(flow -> modelMapper.map(flow, FlowDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(flowDtos);
        }
    }

    @PatchMapping("{flowId}")
    ResponseEntity<Void> updateFlow(@PathVariable int flowId, @RequestBody UpdateFlowDto updateFlowDto) {
        if (flowService.updateFlow(flowId, updateFlowDto.getName())) {
            logger.info("Flow updated");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Flow update failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{flowId}")
    ResponseEntity<Void> deleteFlow(@PathVariable("flowId") int flowId) {
        if (flowService.deleteFlow(flowId)) {
            logger.info("Flow deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.error("Flow could not be found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
