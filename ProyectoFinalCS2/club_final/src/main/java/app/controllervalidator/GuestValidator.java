
package app.controllervalidator;



public class GuestValidator extends CommonsValidator{

    public GuestValidator() {
        super();
    }
      
        public long validId(String Id) throws Exception{
		return super.isValidLong("el id del invitado ", Id);
}

}