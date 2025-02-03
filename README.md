# 🧾 Receipt Processor API

The Receipt Processor API allows users to submit receipt data, assigns a unique ID to each receipt, and calculates points based on predefined rules. The points can be retrieved using the receipt ID. This application is designed to run in-memory, meaning data does not persist between restarts. It is built using Java and packaged with Docker to ensure easy deployment.

## 📌 API Endpoints

### **1️⃣ Process Receipts**
- **Endpoint**: `POST /receipts/process`
- **Description**:
    - Accepts a receipt in JSON format.
    - Generates a unique receipt ID.
    - Stores receipt data in memory.
    - Returns the generated ID.

 **Request Example**:
  ```json
  {
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}
  ```
 **Response Example**:
  ```json
  { "id": "7fb1377b-b223-49d9-a31a-5a02701dd310" }
  ```

---

### **2️⃣ Get Points**
- **Endpoint**: `GET /receipts/{id}/points`
- **Description**:
    - Retrieves the number of points awarded for the given receipt ID.
    - Returns a JSON object containing the total points.

- **Response Example**:
  ```json
  { "points": 109 }
  ```

---

## 📌 How It Works
1. **Receipt Submission**: Users submit receipts via the `/receipts/process` endpoint.
2. **ID Generation**: A unique **UUID** is assigned to each receipt.
3. **Points Calculation**: The API computes points based on predefined rules, such as:
    - Bonus points for specific retailers.
    - Extra points for purchases made at certain times.
    - Points based on item descriptions and total amount.
4. **Retrieve Points**: Users query `/receipts/{id}/points` to get the calculated points.

---

## 🚀 Running the Application

### **1️⃣ Prerequisites**
Ensure **Docker** Desktop is running before proceeding.
This project requires JDK 23. If your installed JDK version is not JDK 23, you need to either install JDK 23 or modify the JDK version in build.gradle and Dockerfile to match your locally installed JDK version for a successful build. (Tested with JDK 19, 21, and 23.)

### **2️⃣ Clone the Repository**
In any directory of your choice, run the following command:
```sh
git clone https://github.com/xiao549336324/receipt-processor_fetch.git
```

### **3️⃣ Build the API**
After cloning the repository, navigate into the project directory and build it:
```sh
cd receipt-processor_fetch
./gradlew build
```

### **4️⃣ Run the API**
After building, run the API:
```sh
docker-compose up --build # Set up and start the Docker containers
```

The API will now be available at `http://localhost:8080`.

---

## 🛠️ Testing the API

### **1️⃣ Process a Receipt**
Open a new terminal window and execute the following commands:

```sh
curl -X POST http://localhost:8080/receipts/process \
     -H "Content-Type: application/json" \
     -d '{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}'
```
Expected response:
Example
```json
{ "id": "7fb1377b-b223-49d9-a31a-5a02701dd310" } 
```

### **2️⃣ Retrieve Points**
Replace {id} with the ID generated in Step 1 above and run the following command:
```sh
curl -X GET http://localhost:8080/receipts/{your_generated_id}/points
```
Expected response:
```json
{ "points": 109 }
```

---

## 🔧 Debugging & Troubleshooting
- Check running containers:
  ```sh
  docker ps
  ```
- View logs:
  ```sh
  docker logs $(docker ps -q -l)
  ```
- Stop the container:
  ```sh
  docker stop <CONTAINER_ID>
  ```
- If port `8080` is already in use, change the mapping:
  ```sh
  docker run -p 9090:8080 receipt-processor
  ```

---

## 📄 Additional Notes
- **No database is required**, as data is stored in memory.
- **Data does not persist** after the application restarts.
- **If using a different port**, ensure you modify all test requests accordingly.

---

## 📞 Contact
For any issues, please contact: `xiaoyoung99@gmail.com`

