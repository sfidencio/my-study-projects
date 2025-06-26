# 🐘 PostgreSQL Installation & Configuration on Ubuntu

## 📋 Summary

- [Install PostgreSQL on Ubuntu](#-install-postgresql-on-ubuntu)
- [Install PostgreSQL with Docker Compose](#-install-postgresql-with-docker-compose)
- [Install PostgreSQL with Docker (no Compose)](#-install-postgresql-with-docker-no-compose)
- [Change postgres User Password](#-change-postgres-user-password)
- [Allow Remote Connections](#️-allow-remote-connections)
- [Connect Using DBeaver or External CLI](#-connect-using-dbeaver-or-external-cli)
- [Basic postgresql.conf Tuning](#️-basic-postgresqlconf-tuning)
- [Security Tips](#-security-tips)
- [Useful Commands](#-useful-commands)
- [Example Usage with psql](#-example-usage-with-psql)
- [References](#-references)

---

## 🚀 Install PostgreSQL on Ubuntu

Install PostgreSQL and check the service status.

```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl status postgresql
```

---

## 🐳 Install PostgreSQL with Docker Compose

Run PostgreSQL easily using Docker and Docker Compose, with persistent data and custom user/password.

### 1. Create a `docker-compose.yml` file

```yaml
version: '3.8'
services:
  db:
    image: postgres:15
    container_name: postgres-db
    restart: always
    environment:
      POSTGRES_USER: myuser         # Change as needed
      POSTGRES_PASSWORD: mypassword # Change as needed
      POSTGRES_DB: mydatabase       # Optional: default database
    ports:
      - "5432:5432"
    volumes:
      - ./postgres-data:/var/lib/postgresql/data
```

- `POSTGRES_USER`: The database user to create (default: `postgres`)
- `POSTGRES_PASSWORD`: The password for the user
- `POSTGRES_DB`: (Optional) The default database to create
- `volumes`: Maps a local folder (`./postgres-data`) to persist data

### 2. Start PostgreSQL

```bash
docker-compose up -d
```

### 3. Connect to PostgreSQL

- **psql CLI:**
  ```bash
  psql -h localhost -U myuser -d mydatabase
  ```
  Enter the password when prompted.

- **DBeaver or other tools:**  
  Use `localhost:5432`, user `myuser`, password `mypassword`, database `mydatabase`.

### 4. Stop and Remove Containers

```bash
docker-compose down
```
*Data is persisted in the `./postgres-data` folder.*

---

## 🐳 Install PostgreSQL with Docker (no Compose)

Run PostgreSQL using a single Docker command, with persistent data and custom user/password.

### 1. Run PostgreSQL Container

```bash
docker run -d \
  --name postgres-db \
  -e POSTGRES_USER=myuser \
  -e POSTGRES_PASSWORD=mypassword \
  -e POSTGRES_DB=mydatabase \
  -p 5432:5432 \
  -v $(pwd)/postgres-data:/var/lib/postgresql/data \
  postgres:15
```

- `POSTGRES_USER`: The database user to create (default: `postgres`)
- `POSTGRES_PASSWORD`: The password for the user
- `POSTGRES_DB`: (Optional) The default database to create
- `-v $(pwd)/postgres-data:/var/lib/postgresql/data`: Persists data in the `postgres-data` folder in your current directory

### 2. Connect to PostgreSQL

- **psql CLI:**
  ```bash
  psql -h localhost -U myuser -d mydatabase
  ```
  Enter the password when prompted.

- **DBeaver or other tools:**  
  Use `localhost:5432`, user `myuser`, password `mypassword`, database `mydatabase`.

### 3. Stop and Remove Container

```bash
docker stop postgres-db
docker rm postgres-db
```
*Data is persisted in the `./postgres-data` folder.*

---

## 🔑 Change postgres User Password

Change the default password for the `postgres` user.

```bash
sudo -i -u postgres
psql
\password postgres
# Enter new password
\q
exit
```

## 🛠️ Allow Remote Connections

Edit configuration files to allow remote access.

1. **Edit `postgresql.conf`**  
   ```bash
   sudo nano /etc/postgresql/<version>/main/postgresql.conf
   ```
   - Set: `listen_addresses = '*'`

2. **Edit `pg_hba.conf`**  
   ```bash
   sudo nano /etc/postgresql/<version>/main/pg_hba.conf
   ```
   - Add for remote access:
     ```
     host    all    all    0.0.0.0/0    md5
     ```
   - For local:
     ```
     local   all    all               md5
     ```

3. **Restart PostgreSQL**
   ```bash
   sudo systemctl restart postgresql
   ```

## 🧑‍💻 Connect Using DBeaver or External CLI

- **DBeaver:**  
  Download and install from [dbeaver.io](https://dbeaver.io/). Create a new PostgreSQL connection and fill in host, port, user, and password.
- **psql CLI (from another machine):**
  ```bash
  psql -h <server_ip> -U postgres -d <database>
  ```

## ⚙️ Basic postgresql.conf Tuning

Adjust memory, connections, and logging settings according to your server.

- **Memory:**
  ```
  shared_buffers = 512MB
  work_mem = 16MB
  maintenance_work_mem = 128MB
  ```
- **Connections:**
  ```
  max_connections = 100
  ```
- **Logging:**
  ```
  logging_collector = on
  log_directory = 'log'
  log_filename = 'postgresql-%a.log'
  log_statement = 'ddl'
  ```

> ⚠️ Always tune according to your server specs and workload!

## 🔒 Security Tips

- Change all default passwords.
- Restrict `listen_addresses` and `pg_hba.conf` to trusted networks.
- Keep PostgreSQL updated.

## 🧹 Useful Commands

```bash
sudo systemctl start postgresql
sudo systemctl stop postgresql
sudo systemctl restart postgresql
sudo systemctl status postgresql
sudo -u postgres psql -c "\l"   # List databases
sudo -u postgres psql -c "\du"  # List users
```

---

## 📝 Example Usage with psql

Below are some basic commands to create a database, table, insert data, and run queries using the `psql` CLI.

### 1. Access psql

```bash
sudo -u postgres psql
```

### 2. Create a Database

```sql
CREATE DATABASE testdb;
\c testdb
```

### 3. Create a Table

```sql
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100)
);
```

### 4. Insert Data

```sql
INSERT INTO users (name, email) VALUES ('Alice', 'alice@email.com');
INSERT INTO users (name, email) VALUES ('Bob', 'bob@email.com');
```

### 5. Query Data

```sql
SELECT * FROM users;
```

### 6. List Tables

```sql
\dt
```

### 7. Describe Table

```sql
\d users
```

### 8. Exit psql

```sql
\q
```

---

### 🖥️ Main psql CLI Commands

Some of the most useful psql commands for daily work.

| Command         | Description                |
|-----------------|---------------------------|
| `\l`            | List databases            |
| `\c dbname`     | Connect to database       |
| `\dt`           | List tables               |
| `\d tablename`  | Describe table structure  |
| `\du`           | List roles/users          |
| `\df`           | List functions            |
| `\dn`           | List schemas              |
| `\x`            | Toggle expanded display   |
| `\timing`       | Toggle query timing       |
| `\h`            | Help on SQL commands      |
| `\?`            | Help on psql commands     |
| `\q`            | Quit psql                 |

---

## 📚 References

- [PostgreSQL Docs](https://www.postgresql.org/docs/)
- [DBeaver](https://dbeaver.io/)
- [PostgreSQL Tuning Guide](https://pgtune.leopard.in.ua/)

---

[⬅️ Back ](../README.md)