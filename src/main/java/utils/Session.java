package utils;

import model.User;

public class Session {
      private static Session session;
      private User user;
      private Session(User user)
      {
          this.user=user;
      }
      public Session Start_session(User user)
      {
          if(this.session==null)
          {
              session=new Session(user);
          }
          return session;
      }
      public void clearSession()
      {
          this.user=null;
      }
      public static  Session getSession()
      {
          return session;
      }
      public  User getUser()
      {
          return this.user;
      }
}
