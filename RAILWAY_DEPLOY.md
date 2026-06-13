# Railway Deployment Guide

## 1. Push Project to GitHub

Push this `taxibooking` folder to a GitHub repository.

## 2. Create Railway Project

1. Open Railway.
2. Create a new project from the GitHub repo.
3. Add a MySQL database service in the same Railway project.

## 3. Required Variables

The app supports Railway MySQL variables automatically:

- `MYSQLHOST`
- `MYSQLPORT`
- `MYSQLDATABASE`
- `MYSQLUSER`
- `MYSQLPASSWORD`
- `PORT`

Railway usually provides these when MySQL is added to the project.

Optional variables:

- `ADMIN_USERNAME=admin`
- `ADMIN_PASSWORD=admin123`
- `SHOW_SQL=false`
- `UPLOAD_DIR=uploads/myserviceimg`

## 4. Build and Start

Railway uses `railway.json`.

Build command:

```bash
bash ./mvnw -DskipTests package
```

Start command:

```bash
java -jar target/taxibooking-0.0.1-SNAPSHOT.jar
```

## 5. Open Live App

After deploy, open Railway generated domain.

Admin login:

- Username: value of `ADMIN_USERNAME`, default `admin`
- Password: value of `ADMIN_PASSWORD`, default `admin123`

## Notes

- Database tables are created automatically because `spring.jpa.hibernate.ddl-auto=update`.
- Service images uploaded from admin are stored in `UPLOAD_DIR`.
- For production, use a strong `ADMIN_PASSWORD`.
