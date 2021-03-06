package com.buildweekjava.watermyplant2;

import com.buildweekjava.watermyplant2.models.*;
import com.buildweekjava.watermyplant2.services.PlantService;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import com.buildweekjava.watermyplant2.services.RoleService;
import com.buildweekjava.watermyplant2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Transactional
@Component
public class SeedData
        implements CommandLineRunner
{

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    PlantService plantService;


    @Transactional
    @Override
    public void run(String[] args) throws
            Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        User u1 = new User("admin",
                           "password",
                           "admin@lambdaschool.local");
        u1.getRoles()
                .add(new UserRoles(u1, r1));
        u1.getRoles()
                .add(new UserRoles(u1, r2));
        u1.getRoles()
                .add(new UserRoles(u1, r3));
        u1.getUseremails()
                .add(new Useremail(u1,
                                   "admin@email.local"));
        u1.getUseremails()
                .add(new Useremail(u1,
                                   "admin@mymail.local"));

        u1.getPlants().add(new Plant(u1, "Forget Me Not", "Myosotis Sylvatica",
                "4 Times a Week in Summer, and Once a week the rest of the year" ));
        u1.getPlants().add(new Plant(u1, "Mexican Sage", "Salvia Leucantha","Water Mexican sage only during periods of drought, when more than two weeks have passed without significant rainfall" ));

        userService.save(u1);

        // data, user
        User u2 = new User("cinnamon",
                           "1234567",
                           "cinnamon@lambdaschool.local");
        u2.getRoles()
                .add(new UserRoles(u2, r2));
        u2.getRoles()
                .add(new UserRoles(u2, r3));
        u2.getUseremails()
                .add(new Useremail(u2,
                                   "cinnamon@mymail.local"));
        u2.getUseremails()
                .add(new Useremail(u2,
                                   "hops@mymail.local"));
        u2.getUseremails()
                .add(new Useremail(u2,
                                   "bunny@email.local"));

        u2.getPlants().add(new Plant(u2, "Forget Me Not", "Myosotis Sylvatica",
                "4 Times a Week in Summer, and Once a week the rest of the year" ));
        u2.getPlants().add(new Plant(u2, "Mexican Sage", "Salvia Leucantha","only during periods of drought, when more than two weeks have passed without significant rainfall" ));

        userService.save(u2);

        // user
        User u3 = new User("barnbarn",
                           "ILuvM4th!",
                           "barnbarn@lambdaschool.local");
        u3.getRoles()
                .add(new UserRoles(u3, r2));
        u3.getUseremails()
                .add(new Useremail(u3,
                                   "barnbarn@email.local"));

        u3.getPlants().add(new Plant(u3, "Forget Me Not", "Myosotis Sylvatica",
                "4 Times a Week in Summer, and Once a week the rest of the year" ));
        u3.getPlants().add(new Plant(u3, "Mexican Sage", "Salvia Leucantha","only during periods of drought, when more than two weeks have passed without significant rainfall" ));
        userService.save(u3);

        User u4 = new User("puttat",
                           "password",
                           "puttat@school.lambda");
        u4.getRoles()
                .add(new UserRoles(u4, r2));
        u4.getPlants().add(new Plant(u4, "Forget Me Not", "Myosotis Sylvatica",
                "4 Times a Week in Summer, and Once a week the rest of the year" ));
        u4.getPlants().add(new Plant(u4, "Mexican Sage", "Salvia Leucantha","only during periods of drought, when more than two weeks have passed without significant rainfall" ));

        userService.save(u4);


        User u5 = new User("misskitty",
                           "password",
                           "misskitty@school.lambda");
        u5.getRoles()
                .add(new UserRoles(u5, r2));

        u5.getPlants().add(new Plant(u5, "Forget Me Not", "Myosotis Sylvatica",
                "4 Times a Week in Summer, and Once a week the rest of the year" ));
        u5.getPlants().add(new Plant(u5, "Mexican Sage", "Salvia Leucantha","only during periods of drought, when more than two weeks have passed without significant rainfall" ));

        userService.save(u5);

        if (false)
        {
            // using JavaFaker create a bunch of regular users
            // https://www.baeldung.com/java-faker
            // https://www.baeldung.com/regular-expressions-java

            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                                                                        new RandomService());
            Faker nameFaker = new Faker(new Locale("en-US"));

            for (int i = 0; i < 25; i++)
            {
                new User();
                User fakeUser;

                fakeUser = new User(nameFaker.name()
                                            .username(),
                                    "password",
                                    nameFaker.internet()
                                            .emailAddress());
                fakeUser.getRoles()
                        .add(new UserRoles(fakeUser, r2));
                fakeUser.getUseremails()
                        .add(new Useremail(fakeUser,
                                           fakeValuesService.bothify("????##@gmail.com")));
                userService.save(fakeUser);
            }
        }
    }
}