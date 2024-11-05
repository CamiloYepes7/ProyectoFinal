package app.dao.interfaces;

import app.dto.GuestDto;
import app.dto.UserDto;

public interface GuestDao  {
    public void createGuest(GuestDto guestDto) throws Exception;
    public void desactivateGuest(GuestDto guestDto) throws Exception;

    /**
     *
     * @param guestDto
     * @throws Exception
     */
    public void activateGuest(GuestDto guestDto)  throws Exception;
    public GuestDto findByUserId(UserDto userDto) throws Exception;
    public void deleteGuest(GuestDto guestDto) throws Exception;
    
}
