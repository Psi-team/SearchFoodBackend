INSERT INTO Users( mail, passwd, sexual, birthyear ) VALUES( "Admin@test1.com", "123343223", 0, 1983 ); 
INSERT INTO Users( mail, passwd, sexual, birthyear ) VALUES( "Admin@test2.com", "123343223", 1, 1983 ); 
INSERT INTO Users( mail, passwd, sexual, birthyear ) VALUES( "Admin@test3.com", "123343223", 1, 1998 ); 
INSERT INTO Users( mail, passwd, sexual, birthyear ) VALUES( "Admin@test4.com", "123343223", 0, 1986 ); 
INSERT INTO Users( mail, passwd, sexual, birthyear ) VALUES( "Admin@test5.com", "123343223", 0, 1963 ); 

INSERT INTO ReferedTable( value, types, class ) VALUES( "三寶飯", "飯", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "滷肉飯", "飯", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "豬排飯", "飯", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "炒飯", "飯", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "燴飯", "飯", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "油飯", "飯", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "陽春麵", "麵食", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "涼麵", "麵食", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "油麵", "麵食", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "義大利麵", "麵食", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "拉麵", "麵食", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "麥當勞", "速食", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "肯德基", "速食", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "胖老爹", "速食", "FoodTypes" ); 
INSERT INTO ReferedTable( value, types, class ) VALUES( "頂呱呱", "速食", "FoodTypes" ); 


INSERT INTO StoreIndo( storename, city. district, address, tel, creator, lat_long ) 
            VALUES("JSP", "台北市","中正區","民生北路三段2號1樓","0222244568", "Admin@test2.com", "{\"lat\":123,\"long\":121}"); 
INSERT INTO StoreIndo( storename, city. district, address, tel, creator, lat_long ) 
            VALUES("JSP", "台北市","中山正區","民生南路三段2號1樓","0222254568", "Admin@test2.com", "{\"lat\":123,\"long\":121}"); 
INSERT INTO StoreIndo( storename, city. district, address, tel, creator, lat_long ) 
            VALUES("JSP", "台北市","中正區","民生北路三段2號1樓","0222244568", "Admin@test1.com", "{\"lat\":123,\"long\":121}"); 
INSERT INTO StoreIndo( storename, city. district, address, tel, creator, lat_long ) 
            VALUES("JSP", "新北市","板橋區","民生北路一段2號1樓","0222235568", "Admin@test2.com", "{\"lat\":123,\"long\":121}"); 
INSERT INTO StoreIndo( storename, city. district, address, tel, creator, lat_long ) 
            VALUES("JSP", "台南市","西區","民生北路三段2號1樓","0222244568", "Admin@test2.com", "{\"lat\":123,\"long\":121}"); 
INSERT INTO StoreIndo( storename, city. district, address, tel, creator, lat_long ) 
            VALUES("JSP", "台中市","東區","民生北路三段2號1樓","0222244568", "Admin@test2.com", "{\"lat\":123,\"long\":121}"); 
INSERT INTO StoreIndo( storename, city. district, address, tel, creator, lat_long ) 
            VALUES("JSP", "台北市","中山區","民生北路三段2號1樓","0222244568", "Admin@test2.com", "{\"lat\":123,\"long\":121}"); 

INSERT INTO BusinessHours(storeId,mon,tue,wed,thu,fri,sat,sun) 
            VALUES(1,"09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","off","off"); 
INSERT INTO BusinessHours(storeId,mon,tue,wed,thu,fri,sat,sun) 
            VALUES(2,"09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","off","off"); 
INSERT INTO BusinessHours(storeId,mon,tue,wed,thu,fri,sat,sun) 
            VALUES(3,"09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","off","off"); 
INSERT INTO BusinessHours(storeId,mon,tue,wed,thu,fri,sat,sun) 
            VALUES(4,"09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","off","off"); 
INSERT INTO BusinessHours(storeId,mon,tue,wed,thu,fri,sat,sun) 
            VALUES(5,"09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","off","off"); 
INSERT INTO BusinessHours(storeId,mon,tue,wed,thu,fri,sat,sun) 
            VALUES(6,"09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","off","off"); 
INSERT INTO BusinessHours(storeId,mon,tue,wed,thu,fri,sat,sun) 
            VALUES(7,"09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","09:30~18:30","off","off"); 

