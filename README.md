# 🧾 Receipt Processor API

The Receipt Processor API allows users to submit receipt data, assigns a unique ID to each receipt, and calculates points based on predefined rules. The points can be retrieved using the receipt ID. This application is designed to run in-memory, meaning data does not persist between restarts. It is built using Java and packaged with Docker to ensure easy deployment.

## 🚀 Running the Application

### **1️⃣ Prerequisites**
Ensure **Docker** is installed on your system.

### **2️⃣ Clone the Repository**
```sh
git clone https://github.com/your-repo/receipt-processor.git
cd receipt-processor
```

### **3️⃣ Build and Run the API**
```sh
docker build -t receipt-processor .
docker run -p 8080:8080 receipt-processor
```

The API will now be available at `http://localhost:8080`.

---

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

## 🛠️ Testing the API

### **1️⃣ Health Check**
To verify if the API is running:
```sh
curl http://localhost:8080/health
```
Expected response:
```json
{"status": "ok"}
```

### **2️⃣ Process a Receipt**
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
```json
{ "id": "7fb1377b-b223-49d9-a31a-5a02701dd310" }
```

### **3️⃣ Retrieve Points**
```sh
curl -X GET http://localhost:8080/receipts/7fb1377b-b223-49d9-a31a-5a02701dd310/points
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
For any issues, please contact: `your.email@example.com`

