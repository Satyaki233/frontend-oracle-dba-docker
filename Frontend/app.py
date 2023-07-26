from flask import Flask,render_template,jsonify,url_for,redirect,request
#from waitress import serve
app =Flask(__name__)
import requests


@app.route('/')
@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/success')
def success():
    return render_template('ok.html')

@app.route('/',methods=['POST'])
def get_loginvalue():
    name=request.form['username']
    password=request.form['password']
    loginjasondata={'name':name,'password':password}
    
    return jsonify(loginjasondata)
    # res = request.post('http://localhost:5000/josondata', json=loginjasondata)

   # print ('response from server:', res.text)
    #dictFromServer = res.json()


@app.route('/register')
def hellow_register():
    return render_template('register.html',item_name='ooo lovely')
@app.route('/register',methods=['POST'])
def get_valueFromRagisterPage():
    name = request.form['name']
    email = request.form['email']
    password=request.form['password']
    phone=request.form['phone']
    address=request.form['address']
    registerjasondata={"name":name,"email":email,'password':password,'phone':phone,'address':address};
    res = requests.post('http://localhost:8080/user', json=registerjasondata)
    print(res.text)
    return redirect(url_for('success'))

#serve(app,host="0.0.0.0",port=50100)
if __name__ == "__main__":
    app.run()