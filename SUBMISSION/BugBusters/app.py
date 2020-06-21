from flask import Flask, render_template, request
app = Flask(__name__)
import pickle
import urllib.parse, urllib.request, urllib.error
import json
# import ssl
import htmlmin
import random
import sqlite3

def render_minified_template(*args, **kwargs):
	return htmlmin.minify(render_template(*args, **kwargs))

keyval = random.randint(0, 999999)

db = sqlite3.connect('main.db')
crsr = db.cursor()

navitems = [
    {
        'name': 'Home',
        'url': '/'
    },
    {
        'name': 'Predict',
        'url': '/predict'
    },
    {
        'name': 'Team',
        'url': '/team'
    },
    {
        'name': 'About',
        'url': '/about'
    },
    {
        'name': "Log",
        'url': '/log'
    }
]

team = [
    {
        'name': 'Harsh Marolia',
        'img': 'harsh.jpg',
        'desc': 'Well, I\'m an undergraduate student, interested in Web and Android Development and I also love doing Competitive Programming on various competitive platforms. 😊',
        'url': 'https://github.com/HarshMarolia'
    },
    {
        'name': 'Paridhi Malav',
        'img': 'paridhi.jpg',
        'desc': 'I\'m an engineering student, interested in Web and Android Development and have been learning the importance of applying classical-strategies to modern-day projects.',
        'url': 'https://github.com/paridhi1314'
    },
    {
        'name': 'Param Siddharth',
        'img': 'param.jpg',
        'desc': 'Hello! I am a simple person who wants to become wise and loves the nature. 🌏❤',
        'url': 'https://www.paramsid.com'
    },
    {
        'name': 'Ritesh Yadav',
        'img': 'ritesh.jpeg',
        'desc': 'A F/OSS enthusiast, trying to contribute to open source projects and communities.<br/>Kaggle Expert | Android Developer | Full Stack Web Developer',
        'url': 'https://github.com/DARK-art108'
    },
    {
        'name': 'Sushant Sharma',
        'img': 'sushant.jpg',
        'desc': 'Hey! I\'m Sushant. Currently I\'m pursuing engineering. I have keen interest in machine learning, but at present I have developed some websites and some Android apps using flutter.',
        'url': 'http://'
    }
]

file = open('model.pkl', 'rb')
clf = pickle.load(file)
file.close()

def keygen():
    # global keyval
    # keyval = random.randint(0, 999999)
    return keyval

@app.route('/')
def homepage():
    keygen()
    return render_minified_template('index.html', keyval=keyval, navitems=navitems, currentitem='Home')

@app.route('/predict', methods=["GET","POST"])
def predictpage():
    if request.method == "POST":
        myDict = request.form
        fever = float(myDict['fever'])
        age = int(myDict['age'])
        pain = int(myDict['pain'])
        runnyNose = int(myDict['runnyNose']) 
        diffBreath = int(myDict['diffBreath']) 
        inputFeatures = [fever,pain,age,runnyNose,diffBreath]
        infoProb = clf.predict_proba([inputFeatures])[0, 1]
        # print(infoProb)
        with sqlite3.connect('main.db') as db:
        	crsr = db.cursor()
        	params_toinsert = ', '.join(list(map(str, inputFeatures)))
        	result_toinsert = f"{infoProb:.2f}"
        	crsr.execute(f'INSERT INTO recent (params, result) values ("{params_toinsert}", "{result_toinsert}");')
        	db.commit()
        return render_minified_template('show.html',inf=round(infoProb*100, 1), navitems=navitems, currentitem='Predict')
    return render_minified_template('pquery.html', navitems=navitems, currentitem='Predict')

@app.route('/about')
def about():
    return render_minified_template('about.html', navitems=navitems, currentitem='About')

@app.route('/team')
def teampage():
    return render_minified_template('team.html', team=team, navitems=navitems, currentitem='Team')

@app.route('/news.json', methods=['GET'])
def newsjson():
    global keyval
    currentkey = keyval
    keygen()
    if request.method == 'GET':
        # print('\n\n\n\n', request.args.get('key'))
        if request.args.get('key') == str(currentkey):
            from apikey import apikey
            try:
            	data = urllib.request.urlopen('http://newsapi.org/v2/everything?'
            + urllib.parse.urlencode({
                'q': 'covid-19',
                'apiKey': apikey
            })).read()
            
            	return data
            except:
            	return '{"status": "FAIL"}'
            # return '{"status": "OK", "response": "Got it!"}'
        else:
            return '{"status": "FAIL"}'
    return '{"status": "FAIL"}'

@app.route('/log')
def logpage():
    with sqlite3.connect('main.db') as db:
    	crsr = db.cursor()
    	crsr.execute('SELECT params, result FROM recent ORDER BY pkey DESC LIMIT 10;')
    	logvalues = crsr.fetchall() 
    	# print(logvalues)
    	infec = [(i[0], i[1], float(i[1]) >= 0.75) for i in logvalues]
    	print(infec)
    	return render_minified_template('log.html', navitems=navitems, currentitem='Log', items=infec)
    return 'Error.'

if __name__ == "__main__":
    app.run(debug=True)
