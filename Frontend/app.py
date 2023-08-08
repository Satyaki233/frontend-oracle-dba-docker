from flask import Flask,render_template,jsonify,url_for,redirect,request,session
import json
import re
app =Flask(__name__)
import requests
app.secret_key="login"





@app.route('/success')
def success():
    return render_template('ok.html')


#main project work start from  here

@app.route('/')
@app.route('/rcciitfest')
def home():
    return render_template('home.html')


@app.route('/login')
def show_login_page():
    return render_template('login.html')

@app.route('/login', methods=['POST'])
def show_login():
    name = request.form['username']
    phoneno = request.form['phoneno']
    logindata={"name":name,"phoneNumber":phoneno}
    print(logindata)
    
    res = requests.post('http://localhost:8080/members/login', json=logindata)
    data=json.loads(res.text)
    print(data)
    value=data["data"]
    if  value !=None and value[0]['name']== name and value[0]['phoneNumber']== phoneno:
        session["username"]= name
        session["phoneno"] = phoneno
        return redirect(url_for('show_participent_page'))

    else:
        session["username"] =""
        session["phoneno"] = ""
        print("User not found",session["username"])
        return render_template('login.html',value="Not Found")

@app.route('/register')
def show_register_page():
    return render_template('register.html')



@app.route('/register',methods=['POST'])
def get_valueFromRagisterPage():
    name = request.form['name']
    email = request.form['email']
    phone=request.form['phone']
    rolevalue=request.form['menu']
    role=None
    if rolevalue=='1':
        role='VOLUNTEER'
    elif rolevalue=='2':
        role='PARTICIPANTS'
    elif rolevalue=='3':
        role='AUDIENCE'

    
    registerjasondata={"name":name,'email':email,'phoneNumber':phone,'role':role}
    print(registerjasondata)
    res = requests.post('http://localhost:8080/members/register', json=registerjasondata)
    data=json.loads(res.text)
    print(data)
    value=data["data"]
    if value==None:
        return render_template('register.html',value="Insert proper value")

    return redirect(url_for('show_login_page'))


@app.route('/participent')
def show_participent_page():
    if  session["username"] !="" and session["phoneno"] != "":
        headings=("id","name","email","phoneno","role")
        res=requests.get('http://localhost:8080/members/all')
        data=json.loads(res.text)
        value=data["data"]
        print(value)
        return render_template('participent.html',headings=headings,data=value)
    else:
        return redirect(url_for('show_login'))

    

@app.route('/logout')
def logout_function():
    session["username"] =""
    session["phoneno"]  = ""
    return redirect(url_for('home'))


    





if __name__ == "__main__":
    app.run()