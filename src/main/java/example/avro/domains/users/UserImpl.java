package example.avro.domains.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserImpl implements UserProto {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserImpl.class);

    @Override
    public User add(CharSequence name, int age) {
        LOGGER.info("initiating add user name: "+ name +" age : "+ age);

        User user = new User();
        user.setId(1L);
        user.setAge(age);
        user.setName(name);

        LOGGER.info("completed add user id : "+ user.getId() +" name: "+ user.getName() +" age : "+ user.getAge());

        return user;
    }

    @Override
    public User getById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

}
