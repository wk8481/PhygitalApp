package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.FlowDto;
import be.kdg.team_5_phygital.controller.api.dto.NewFlowDto;
import be.kdg.team_5_phygital.controller.api.dto.UpdateFlowDto;
import be.kdg.team_5_phygital.domain.Flow;
import be.kdg.team_5_phygital.repository.FlowRepository;
import be.kdg.team_5_phygital.service.FlowService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flows")
public class FlowsController {
    private final FlowService flowService;
    private final FlowRepository flowRepository;
    private final ModelMapper modelMapper;

    public FlowsController(FlowService flowService, FlowRepository flowRepository, ModelMapper modelMapper) {
        this.flowService = flowService;
        this.flowRepository = flowRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("{projectId}")
    ResponseEntity<FlowDto> saveFlow(@PathVariable int projectId, @RequestBody @Valid NewFlowDto flowDto) {
        if (flowRepository.findByName(flowDto.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Flow createdFlow = flowService.saveFlow(flowDto.getName());
        return new ResponseEntity<>(modelMapper.map(createdFlow, FlowDto.class), HttpStatus.CREATED);
    }

    @PatchMapping("{flowId}")
    ResponseEntity<Void> updateFlow(@PathVariable int flowId, @RequestBody UpdateFlowDto updateFlowDto) {
        if (flowService.updateFlow(flowId, updateFlowDto.getName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{flowId}")
    ResponseEntity<Void> deleteFlow(@PathVariable("flowId") int flowId) {
        if (flowService.deleteFlow(flowId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
