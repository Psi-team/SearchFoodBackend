
/* sudo mysql -u 'user' -p  */ 

/* 
CREATE DATABASE IF NOT EXISTS SearchFood; 
USE SearchFood; 
*/ 

/* Create the user table */ 
/* CHAR() 是固定字元存取,VARCHAR()是以實際字元存取,
 * VARCHAR()雖然比較省空間,但若要修改可能會被擷取分段擺放 
 * VARCHAR()也不能有給定太大的空間,雖然是以實際字元數量儲存,但是Query是會以定義的數量來搜尋,因此太大的空間會消耗記憶體空間 */ 
CREATE TABLE IF NOT EXISTS Users( 
    userId INT NOT NULL AUTO_INCREMENT, 
    username VARCHAR(20) NOT NULL, 
    mail VARCHAR(25) NOT NULL, 
    passwd CHAR(10) NOT NULL, 
    sexual INT NOT NULL, 
    birthyear YEAR NOT NULL, 
    age TINYINT DEFAULT ( YEAR(CURRENT_DATE) - birthyear ), 
    PRIMARY KEY( userId ),  
    UNIQUE KEY mail ( mail ), 
    UNIQUE KEY username ( username ) 
); 

/* Create the User token */
CREATE TABLE IF NOT EXISTS Token( 
    mail VARCHAR(25) NOT NULL, 
    username VARCHAR(20) NOT NULL, 
    token VARCHAR(80) NOT NULL,
    login_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    navigator_type VARCHAR(20) NOT NULL, 
    PRIMARY KEY( mail, navigator_type ), 
    FOREIGN KEY( mail ) REFERENCES Users( mail ), 
    FOREIGN KEY( username ) REFERENCES Users( username ) 
); 

/* Create the Store information */ 
/* 當設定欄位的編碼時,不能使用NOT NULL語法 */ 
CREATE TABLE IF NOT EXISTS StoreInfo( 
    storeid INT NOT NULL AUTO_INCREMENT,
    storename VARCHAR(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    city CHAR(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    district CHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    address VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    tel CHAR(10) NOT NULL, 
    /*created_date DATE NULL DEFAULT (CURRENT_DATE), */ /* 時間格式預設當天日期, CURRENT_DATE一定要用() */ 
    createdAt TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    creator VARCHAR(20) NOT NULL, 
    click_week INT(3) DEFAULT '0', 
    click_cum INT(4) DEFAULT '0', 
    lat_long JSON NOT NULL, 
    rating FLOAT DEFAULT '0.0', 
    logo VARCHAR(150) NULL, 
    images VARCHAR(150) NULL,
    slogan VARCHAR(50) NULL, 
    tags VARCHAR(150) NOT NULL, 
    PRIMARY KEY( storeId, storename, city, district, address ), 
    /*UNIQUE KEY store_name ( store_name, city, district, address ), */ 
    UNIQUE KEY city ( city, district, address ), 
    FOREIGN KEY( creator ) REFERENCES Users( username ) 
);

/* Create the food table */ 
CREATE TABLE IF NOT EXISTS ReferedTable( 
    id INT NOT NULL AUTO_INCREMENT, 
    value VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    types VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    class VARCHAR(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    PRIMARY KEY( id ) 
); 

/* Create BusinessHours */ 
CREATE TABLE IF NOT EXISTS BusinessHours( 
    storeId INT NOT NULL, 
    mon CHAR(11) NULL, 
    tue CHAR(11) NULL, 
    wed CHAR(11) NULL, 
    thu CHAR(11) NULL, 
    fri CHAR(11) NULL, 
    sat CHAR(11) NULL, 
    sun CHAR(11) NULL, 
    PRIMARY KEY( storeId ), 
    FOREIGN KEY( storeId ) REFERENCES StoreInfo( storeId ) 
);

/* Create the StoreTypes table */ 
CREATE TABLE IF NOT EXISTS StoresMenu( 
    storeId INT NOT NULL, 
    foodId INT NOT NULL, 
    PRIMARY KEY( storeId, foodId ), 
    FOREIGN KEY( storeId ) REFERENCES StoreInfo( storeId ), 
    FOREIGN KEY( foodId ) REFERENCES ReferedTable( id )  
); 


/* Create the comments of Store */ 
CREATE TABLE IF NOT EXISTS StoreComment( 
    storeId INT NOT NULL,
    username VARCHAR(20) NOT NULL, 
    star FLOAT NOT NULL, 
    price VARCHAR(20) NOT NULL, 
    commentAt TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, 
    comments TINYTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    picture VARCHAR(150) NULL, 
    PRIMARY KEY( username, storeId ), 
    FOREIGN KEY( username ) REFERENCES Users( username ),
    FOREIGN KEY( storeId ) REFERENCES StoreInfo( storeId )
    /* The use of composite primary key, so you need a composite foreign key. */ 
    /*FOREIGN KEY( store_name, city, district, address ) REFERENCES StoreInfo( store_name, city, district, address ) */ 
); 
 
/* Records the click time a day to specific storeId */ 
CREATE TABLE IF NOT EXISTS ClickRecords( 
    username VARCHAR(20) NOT NULL, 
    storeId INT NOT NULL, 
    FOREIGN KEY( username ) REFERENCES Users( username ),
    FOREIGN KEY( storeId ) REFERENCES StoreInfo( storeId ), 
    UNIQUE KEY records ( username, storeId ) 
); 

/* saving the images of stores */ 
CREATE TABLE IF NOT EXISTS StoreImages( 
    storeId INT NOT NULL, 
    picUrl VARCHAR(150) NULL, 
    FOREIGN KEY( storeId ) REFERENCES StoreInfo( storeId )
); 

/* auto delete data when a new day starts */ 
/*  ref: https://noter.tw/2306/mysql%E6%8E%92%E7%A8%8Bevent-scheduler/    Not DELETE EVENTS but DROP EVENTS */ 
CREATE EVENT AutoCleanDay 
    ON SCHEDULE EVERY 1 DAY STARTS DATE(CURRENT_TIMESTAMP) + 1 
    DO 
        DELETE FROM ClickRecords; 


