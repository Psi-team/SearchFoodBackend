
/* sudo mysql -u 'user' -p  */ 

/* Create the user table */ 
/* CHAR() 是固定字元存取,VARCHAR()是以實際字元存取,
 * VARCHAR()雖然比較省空間,但若要修改可能會被擷取分段擺放 
 * VARCHAR()也不能有給定太大的空間,雖然是以實際字元數量儲存,但是Query是會以定義的數量來搜尋,因此太大的空間會消耗記憶體空間 */ 
CREATE TABLE IF NOT EXISTS Users( 
    mail VARCHAR(25) NOT NULL, 
    passwd CHAR(10) NOT NULL, 
    sexual INT NOT NULL, 
    birthyear YEAR NOT NULL, 
    age TINYINT DEFAULT ( YEAR(CURRENT_DATE) - birthyear ), 
    PRIMARY KEY( mail ) 
); 

/* Create the User token */
CREATE TABLE IF NOT EXISTS Token( 
    mail VARCHAR(25) NOT NULL, 
    token VARCHAR(80) NOT NULL,
    login_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    navigator_type VARCHAR(20) NOT NULL, 
    PRIMARY KEY( mail ), 
    FOREIGN KEY( mail ) REFERENCES Users( mail ) 
); 

/* Create the Store information */ 
/* 當設定欄位的編碼時,不能使用NOT NULL語法 */ 
CREATE TABLE IF NOT EXISTS StoreInfo( 
    store_id INT NOT NULL AUTO_INCREMENT,
    store_name VARCHAR(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    city CHAR(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    district CHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    address VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    tel CHAR(10) NOT NULL, 
    created_date DATE NULL DEFAULT (CURRENT_DATE), /* 時間格式預設當天日期, CURRENT_DATE一定要用() */ 
    creator VARCHAR(25) NOT NULL, 
    business_time JSON NULL, 
    click_week INT(3) DEFAULT '0', 
    click_cum INT(4) DEFAULT '0', 
    lat_long JSON NOT NULL, 
    types JSON NOT NULL, 
    PRIMARY KEY( store_id, store_name, city, district, address ), 
    UNIQUE KEY( store_name, city, district, address ), 
    FOREIGN KEY( creator ) REFERENCES Users( mail )
);

/* Create the comments of Store */ 
CREATE TABLE IF NOT EXISTS StoreComment( 
    store_id INT NOT NULL,
    username VARCHAR(25) NOT NULL, 
    star TINYINT NOT NULL DEFAULT '0', 
    price INT NOT NULL, 
    comment_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, 
    comments TINYTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci, 
    picture VARCHAR(20) NULL, 
    PRIMARY KEY( username, store_id ), 
    FOREIGN KEY( username ) REFERENCES Users( mail ),
    FOREIGN KEY( store_id ) REFERENCES StoreInfo( store_id )
    /* The use of composite primary key, so you need a composite foreign key. */ 
    /*FOREIGN KEY( store_name, city, district, address ) REFERENCES StoreInfo( store_name, city, district, address ) */ 
); 
 

