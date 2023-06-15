## How to Run ML Model to Generate Keywords Recommender

### 1. Clone this repository branch 
```
git clone -b ML https://github.com/ChiefAdit/Bangkit.git
```

### 2. Open the cloned repository
- Open with Visual Studio Code

### 3. Create and save an .env file in the root directory as follows
```
FLASK_APP=main.py
```

### 4. Run the program
```
flask run
```

### 5. Check the status
- Open http://127.0.0.1:5000 with the browser
- If "OK" is displayed, then the program has successfully run

### 6. Run the result
- Now execute the above link with POST method with following conditions:
```
POST http://127.0.0.1:5000
Content-Type: application/json

{
    "search": "<type keyword here>"
}
```
