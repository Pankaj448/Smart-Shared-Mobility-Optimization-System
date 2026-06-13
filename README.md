# Taxi Booking System

Spring Boot taxi booking website with public booking/contact forms and a secured admin dashboard.

## Requirements

- Java 17 or newer
- MySQL running locally or reachable through a JDBC URL

## Database

Create the database before starting the app:

```sql
CREATE DATABASE taxibooking;
```

Default connection values are:

- URL: `jdbc:mysql://localhost:3306/taxibooking`
- Username: `root`
- Password: `root`

You can override them without editing code:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/taxibooking"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="root"
```

## Run

```powershell
.\mvnw.cmd spring-boot:run
```

Open `http://localhost:8080`.

## Admin Login

The first admin account is created automatically when the `admin` table is empty.

- Username: `admin`
- Password: `admin123`

Override first-run defaults with:

```powershell
$env:ADMIN_USERNAME="admin"
$env:ADMIN_PASSWORD="admin123"
```

After login, use `/admin/dashboard` to manage bookings, contacts, services, and credentials.

## Ride Sharing

The project now includes a backend-connected ride sharing module.

- Public page: `http://localhost:8080/ride-sharing`
- Form endpoint: `/rideshareform`
- Admin list: `/admin/readAllRideShares`
- Database table: `ride_share_request`

Ride sharing requests are saved through Spring Data JPA and can be reviewed or deleted from the admin dashboard.

## Smart Features

- Auto driver assignment: booking and ride sharing submissions assign a driver and vehicle from the backend.
- Shortest path finder: `/shortest-path` uses a Java Dijkstra algorithm service to calculate the shortest route, distance, estimated time, and visual route map.
- Admin analytics: `/admin/dashboard` shows live backend metrics from MySQL.
