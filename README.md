# XMA 
The XMA app was build as a project in the module Programming 3 at Imperial College London. The app is designed to take in information about eczema symptoms from the user and then display the according relationship between severity and weather. 
The user is asked to select where on the body they had symptoms and select treatment used, if any. A graph is created with severity against time, temperature, humidity, pollen, and pollution. 

### Notes about running the app on an Emulator
Thoroughout this process, we have noticed differences in the app between Emulators. To view the best looking form of the app, use a Pixel 2 virtual device or download on an Android device. 


### Instructions for obtaining BreezoMeter API Key
Each API key obatined with a free account will only be valid for two weeks. If you find that all of the weather information results in 'Error' rather than values, the API key may have expired. To obtain a new key, visit https://developers.breezometer.com/signup to create a free account. Once you have signed up, visit https://developers.breezometer.com/dashboard/ and copy the API key for your account. 

In the MoreDetailsSymptomsActivity.java file, line 55, change the API_KEY field to your API key. (app/src/main/java/com/example/eczema_app/ui/log/MoreDetailsSymptomActivity.java)

Note: Pollen information is not always available for every location. 
