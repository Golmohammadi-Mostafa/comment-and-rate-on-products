# 
After running the program, 
you can use this username & password for ROLE_ADMIN  -->  ( username: admin  and   pass:123 )
and this user for client or USER_ROLE  (username: user and password :123 )

When the program is running on port 8080 you can see all Rest APIs: http://localhost:8081/swagger-ui.html#

you can get token with role admin by this api: http://localhost:8080/users/signin?password=123&username=admin
and also for token with role user you can use this api: http://localhost:8081/users/signin?password=123&username=user

for insert data into database for category, product and comments you can
run script-inser-data.sql file in resource folder.


