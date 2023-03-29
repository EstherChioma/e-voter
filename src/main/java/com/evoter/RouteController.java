package com.evoter;

import com.evoter.candidate.model.Candidate;
import com.evoter.candidate.service.CandidateService;
import com.evoter.party.model.Party;
import com.evoter.party.service.PartyService;
import com.evoter.poll.model.Poll;
import com.evoter.poll.service.PollService;
import com.evoter.pollType.model.PollType;
import com.evoter.pollType.service.PollTypeService;
import com.evoter.user.Role;
import com.evoter.user.dto.UserLoginDto;
import com.evoter.user.model.User;
import com.evoter.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class RouteController {
    private final UserService userService;
    private final PollTypeService pollTypeService;
    private final PartyService partyService;
    private final CandidateService candidateService;
    private final PollService pollService;

    public RouteController(UserService userService, PollTypeService pollTypeService, PartyService partyService, CandidateService candidateService, PollService pollService) {
        this.userService = userService;
        this.pollTypeService = pollTypeService;
        this.partyService = partyService;
        this.candidateService = candidateService;
        this.pollService = pollService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "E-Voter - Landing Page");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "E-Voter - Login");
        model.addAttribute("userLoginDetails", new UserLoginDto());
        return "login";
    }

//    @PostMapping("login")
//    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
//        User user = userService.getUserByUsernameAndPassword(username, password);
//        if (user != null) {
//            model.addAttribute("user", user);
//            return "redirect:/";
//        }
//        return "login";
//    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("pageTitle", "E-Voter - Register");
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("pageTitle", "E-Voter - Forgot Password");
        return "forgot-password";
    }

    @GetMapping("/dashboard/{userid}")
    public String dashboard(@PathVariable("userid") Long Id,  Model model) {
        prepareAuthUserForView(Id, model);
        model.addAttribute("pageTitle", "E-Voter - Dashboard");
        return "dashboard";
    }

    private void prepareAuthUserForView(Long Id, Model model) {
        User user = userService.getUserById(Id);
        if (user.getRole() == Role.ADMIN) {
            model.addAttribute("isAdmin", true);
        }
        model.addAttribute("authUser", user);
    }

    @GetMapping("/dashboard/profile/{userid}")
    public String profile(@PathVariable("userid") Long Id, Model model) {
        prepareAuthUserForView(Id, model);
        model.addAttribute("pageTitle", "E-Voter - Profile");
        return "profile";
    }

    @GetMapping("/dashboard/polls/{userid}")
    public String polls(@PathVariable("userid") Long Id, Model model) {
        prepareAuthUserForView(Id, model);
        List<Poll> polls = pollService.getAllPolls();
        model.addAttribute("polls", polls);
        model.addAttribute("pageTitle", "E-Voter - Polls");
        return "polls";
    }

    @GetMapping("/dashboard/add-poll/{userid}")
    public String addPoll(@PathVariable("userid") Long Id, Model model) {
        prepareAuthUserForView(Id, model);
        List<PollType> pollTypes = pollTypeService.getAllPollTypes();
        model.addAttribute("pollTypes", pollTypes);
        model.addAttribute("pageTitle", "E-Voter - Add Poll");
        return "add_poll";
    }

    @GetMapping("/dashboard/vote/{userid}/{pollid}")
    public String vote(@PathVariable("userid") Long Id, @PathVariable("pollid") Long pollId, Model model) {
        prepareAuthUserForView(Id, model);
        Poll poll = pollService.getPollById(pollId);
        List<Candidate> candidates = candidateService.getCandidatesByPollTypeId(poll.getPollTypeId());
        model.addAttribute("candidates", candidates);
        model.addAttribute("poll", poll);
        model.addAttribute("pageTitle", "E-Voter - Vote");
        return "vote";
    }

    @GetMapping("/dashboard/add-admin/{userid}")
    public String addAdmin(@PathVariable("userid") Long Id, Model model) {
        prepareAuthUserForView(Id, model);
        model.addAttribute("pageTitle", "E-Voter - Add Admin");
        return "add_admin";
    }

    @GetMapping("/dashboard/add-candidate/{userid}")
    public String addCandidate(@PathVariable("userid") Long Id, Model model) {
        prepareAuthUserForView(Id, model);
        List<PollType> pollTypes = pollTypeService.getAllPollTypes();
        model.addAttribute("pollTypes", pollTypes);
        List<Party> parties = partyService.getAllParties();
        model.addAttribute("parties", parties);
        model.addAttribute("pageTitle", "E-Voter - Add Candidate");
        return "add_candidate";
    }

    @GetMapping("/dashboard/add-poll-type/{userid}")
    public String addPollType(@PathVariable("userid") Long Id, Model model) {
        prepareAuthUserForView(Id, model);
        model.addAttribute("pageTitle", "E-Voter - Add Poll Type");
        return "add_poll_type";
    }

    @GetMapping("/dashboard/add-party/{userid}")
    public String addParty(@PathVariable("userid") Long Id, Model model) {
        prepareAuthUserForView(Id, model);
        model.addAttribute("pageTitle", "E-Voter - Add Poll Type");
        return "add_party";
    }

//    @GetMapping("/error")
//    public String error(Model model) {
//        model.addAttribute("pageTitle", "E-Voter - Error");
//        return "error";
//    }
}
