// This interface is to check whether the specified data has already existed in tables. 
/* 
package com.searchfood.SearchFoodBackend.utils; 

public class FindDataAbc{ 

    public int save(); 

    public int isExistInUsers( String queryStatements, String...fieldName ){ 
        // define an abstract function that return the id of data in table or -1 for not found. 
        try{ 
            jdbc.queryForObject( 
                    "SELECT * FROM Users WHERE "+query, 
                    new RowMapper<SignUpMember>(){ 
                        @Override 
                        public SignUpMember mapRow( ResultSet rs, int rowNum ) throws SQLException{ 
                            return new SignUpMember(
                                rs.getString("mail"), 
                                rs.getString("passwd"), 
                                rs.getInt("sexual"), 
                                rs.getDate("birthyear").getYear() + 1900, 
                                rs.getInt("age")
                            ); 
                        } 
                    },
                    signupmember.getUsername() ); 
            System.out.println("The member has existed."); 
            return 1; // the user who is trying to sign up has already exist in Users. 
        }catch( EmptyResultDataAccessException e ){ 
            System.out.println("The user is exactly a new member."); 
            return -1; // The user is exactly a new member. 
        } 
        
    } 

} 

*/ 
