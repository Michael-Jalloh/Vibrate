# Vibrate
This is a android vibration module for the godot 3.1 engine.

## Getting Started
Download the source of [godot 3.1](https://github.com/godotengine/godot/releases/tag/3.1-stable)
The repo should be downloaded and the `vibrate` folder should be copied into the `godot modules` folder.
~~~~
mkdir Dev
cd Dev
git clone https://github.com/Michael-Jalloh/Vibrate.git
~~~~

move the downloaded extracted godot source file to the Dev folder. The folder should look liks this.
~~~~
godot-3.1-stable
Vibrate
~~~~

Move the `vibrate` in the `Vibrate` inside the `modules` folder of godot then compile for both your platform and for android by running the commands below

~~~
cp -r Vibrate/vibrate godot-3.1-stable/modules
cd godot-3.1-stable
scons platform=x11
scons platform=android target=release_debug
cd platform/android/java
./gradlew build
~~~

The above commands will compile godot from source and create android export that should be used in your project.
 
 open `godot.project` and add to the bottom 
 ~~~
[android]
modules="org/godotengine/godot/Vibrate"
 ~~~ 

actual usage

~~~
extends TouchScreenButton

var module = null

func _ready():
	connect("pressed",self, "button_pressed")
	if(Engine.has_singleton("Vibrate")):
		print("Has Vibrator")
		module = Engine.get_singleton("Vibrate")
	else:
		print("Has no Vibrator")

func button_pressed():
	print("Button is pressed")
	if(module):
		# Simple Vibration
		module.vibrate(500)
		print("Simple vibration")
~~~

~~~
extends TouchScreenButton

var module = null

func _ready():
	connect("pressed",self, "button_pressed")
	if(Engine.has_singleton("Vibrate")):
		print("Has Vibrator")
		module = Engine.get_singleton("Vibrate")
	else:
		print("Has no Vibrator")

func button_pressed():
	print("Button is pressed")
	if(module):
		# Vibrate with a pattern
		# Check android Vibration
		module.pattern([0, 200,500,1000])
		print("Pattern vibration")
~~~
