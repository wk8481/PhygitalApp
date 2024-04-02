package be.kdg.team_5_phygital.controller.mvc;

import be.kdg.team_5_phygital.controller.mvc.viewmodel.InstallationViewModel;
import be.kdg.team_5_phygital.service.InstallationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/installation")
public class InstallationController {

    private final InstallationService installationService;

    public InstallationController(InstallationService installationService) {
        this.installationService = installationService;
    }

    @GetMapping("login")
    public String showLogin() {
        return "installation/login";
    }

    @GetMapping({"dashboard", "", "/"})
    public String getDashboardPage() {
        return "installation/dashboard";
    }

    @GetMapping("flow-selection")
    public ModelAndView getFlowSelectionPage() {
        var mav = new ModelAndView();
        mav.setViewName("installation/flow-selection");
        mav.addObject("all_flows",
                installationService.getAllInstallations()
                        .stream()
                        .map(installation -> new InstallationViewModel(
                                installation.getId(),
                                installation.isRunning(),
                                installation.getUser(),
                                installation.getProjects()))
                        .toList());
        return mav;
    }

//    @GetMapping("flow")
//    public ModelAndView getOneFlow() {
//        var mav = new ModelAndView();
//        mav.setViewName("flow");
//        mav.addObject("one_flow",
//                new InstallationViewModel(
//                        installationService.getInstallation(1).getId(),
//                        installationService.getInstallation(1).isRunning(),
//                        installationService.getInstallation(1).getUser(),
//                        installationService.getInstallation(1).getProjects()));
//        return mav;
//    }

    @GetMapping("contact-details")
    public String getContactDetailsPage() {
        return "installation/contact-details";
    }
}
