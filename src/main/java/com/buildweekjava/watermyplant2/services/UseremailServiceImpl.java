package com.buildweekjava.watermyplant2.services;

import com.buildweekjava.watermyplant2.exceptions.ResourceNotFoundException;
import com.buildweekjava.watermyplant2.models.User;
import com.buildweekjava.watermyplant2.models.Useremail;
import com.buildweekjava.watermyplant2.repository.UseremailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "useremailService")
public class UseremailServiceImpl
        implements com.buildweekjava.watermyplant2.services.UseremailService
{

    @Autowired
    private UseremailRepository useremailrepos;


    @Autowired
    private com.buildweekjava.watermyplant2.services.UserService userService;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public List<Useremail> findAll()
    {
        List<Useremail> list = new ArrayList<>();

        useremailrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Useremail findUseremailById(long id)
    {
        return useremailrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Useremail with id " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (useremailrepos.findById(id)
            .isPresent())
        {
            if (helperFunctions.isAuthorizedToMakeChange(useremailrepos.findById(id)
                .get()
                .getUser()
                .getUsername()))
            {
                useremailrepos.deleteById(id);
            }
        } else
        {
            throw new ResourceNotFoundException("Useremail with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Useremail update(
            long useremailid,
            String emailaddress)
    {
        if (useremailrepos.findById(useremailid)
            .isPresent())
        {
            if (helperFunctions.isAuthorizedToMakeChange(useremailrepos.findById(useremailid)
                .get()
                .getUser()
                .getUsername()))
            {
                Useremail useremail = findUseremailById(useremailid);
                useremail.setUseremail(emailaddress.toLowerCase());
                return useremailrepos.save(useremail);
            } else
            {
                // note we should never get to this line but is needed for the compiler
                // to recognize that this exception can be thrown
                throw new ResourceNotFoundException("This user is not authorized to make change");
            }
        } else
        {
            throw new ResourceNotFoundException("Useremail with id " + useremailid + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Useremail save(
            long userid,
            String emailaddress)
    {
        User currentUser = userService.findUserById(userid);

        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
            Useremail newUserEmail = new Useremail(currentUser,
                emailaddress);
            return useremailrepos.save(newUserEmail);
        } else
        {

            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }
}
