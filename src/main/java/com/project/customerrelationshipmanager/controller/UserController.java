package com.project.customerrelationshipmanager.controller;

import com.project.customerrelationshipmanager.helper.Message;
import com.project.customerrelationshipmanager.model.Contact;
import com.project.customerrelationshipmanager.model.User;
import com.project.customerrelationshipmanager.repository.ContactRepository;
import com.project.customerrelationshipmanager.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import javax.swing.text.html.Option;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @ModelAttribute
    public void commonData(Model model, Principal principal) {
        String name = principal.getName();
        System.out.println(name);
        User user = userRepository.getUserByUserEmail(name);
        model.addAttribute("user", user);
    }

    @GetMapping("/user_dashboard")
    public String userDashboard(Model model, Principal principal) {
        commonData(model, principal);
        model.addAttribute("title", "User Dashboard - TheHuddle");
        return "user/user_dashboard";
    }

    @GetMapping("/add_contact")
    public String addContact(Model model, Principal principal) {
        commonData(model, principal);
        model.addAttribute("contact",new Contact());
        model.addAttribute("title", "Add Contacts - TheHuddle");
        return "user/add_contact";
    }

    @GetMapping("/user_detail")
    @ResponseBody
    public String userDetail() {

        User user = userRepository.getUserByUserEmail("simpi123@gmail.com");
        System.out.println(user);
        return "user_detail";
    }

    @PostMapping("/process_add_contact")
    public String processAddContact(@Valid @ModelAttribute("contact") Contact contact,
                                    BindingResult bindingResult,
                                    @RequestParam("image") MultipartFile file,
                                    HttpSession session,
                                    Principal principal) {
        if(bindingResult.hasErrors())
        {
            System.out.println(bindingResult);
            return "/user/add_contact";
        }
        try {
            System.out.println(contact);
            String name = principal.getName();
            User user = userRepository.getUserByUserEmail(name);
            String imageName;
            if(file.isEmpty())
            {
                imageName = "person-reference.jpg";
                contact.setContactImage(imageName);
            }
            else
            {
                System.out.println(file.getOriginalFilename());
                imageName = UUID.randomUUID() + ".jpeg";
                System.out.println(imageName);
                contact.setContactImage(imageName);
            }

            contact.setUser(user);
            user.getContactList().add(contact);
            File imageFile = new ClassPathResource("/static/image").getFile();
            Path path = Paths.get(imageFile.getAbsolutePath() + File.separator + imageName);
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image uploaded");
            userRepository.save(user);
            System.out.println("contact added");
            session.setAttribute("message",new Message("Contact successfully added to your contact list","alert-success"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            session.setAttribute("message",new Message("Something went wrong!!","alert-danger"));
        }
        return "redirect:/user/add_contact";
    }

    @GetMapping("/view_contact/{view_contact_page}")
    public String viewContact(@PathVariable("view_contact_page") int page,Model model,Principal principal)
    {
        model.addAttribute("title","View Contacts - TheHuddle");
        String name = principal.getName();
        User user = userRepository.getUserByUserEmail(name);
        int userId = user.getUserId();
        Pageable pageable = PageRequest.of(page,10);
        Page<Contact> userContactList = contactRepository.findContactByUserId(userId,pageable);
        for(Contact contact : userContactList)
        {
            System.out.println(contact.getContactName());
        }
        model.addAttribute("userContactList",userContactList);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",userContactList.getTotalPages());
        return "/user/view_contact";
    }

    @GetMapping("/contact/{contactId}")
    public String contact(@PathVariable("contactId") int contactId,Model model,Principal principal)
    {
        String name = principal.getName();
        int userId = userRepository.getUserByUserEmail(name).getUserId();

        if(!contactRepository.isContactExistByUserId(contactId,userId))
        {
            System.out.println("no value present");
            model.addAttribute("contactError","No contact information  in your contact list for the data provided.");
            return "user/contact";
        }

        Optional<Contact> contactOptional = contactRepository.findContactByUserId(contactId,userId);
        Contact contact = contactOptional.get();
        model.addAttribute("title","Contact Profile - TheHuddle");
        model.addAttribute("contact",contact);
        System.out.println(contact);
        return "/user/contact";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(Principal principal)
    {
        String name = principal.getName();
        User user = userRepository.getUserByUserEmail(name);
        int userId = user.getUserId();
        boolean val = contactRepository.isContactExistByUserId(702,userId);
        System.out.println(val);
        return "working";
    }
}
