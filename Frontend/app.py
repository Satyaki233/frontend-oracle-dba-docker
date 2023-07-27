from flask import Flask,render_template,jsonify,url_for,redirect,request
import re
app =Flask(__name__)
import requests



@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/success')
def success():
    return render_template('ok.html')





@app.route('/login',methods=['POST'])
def get_loginvalue():
    name=request.form['username']
    password=request.form['password']
    if re.match('[a-z|A-Z]',name)==None:
        print("kfjdkf")
        return render_template('login.html',item_name='ooo lovely')
    else:
        loginjasondata={'name':name,'password':password}
    
    
        res = requests.post('http://localhost:8080/logindata', json=loginjasondata)
        print(res.text)
        return redirect(url_for('success'))


    


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
    p=dict(res.text)
    print(type(p))
    print(p)
    return redirect(url_for('success'))

if __name__ == "__main__":
    app.run()