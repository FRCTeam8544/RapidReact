# FRC 8544 Team Documentation (2022 - 2023)

## Topics
---
### Development workstation setup steps

* Refer to the installation guide for WPILib to install VS Code with the WPILib addons. [Link to the document](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html)
* Download the packages for your operating system from [Github link](https://github.com/wpilibsuite/allwpilib/releases)
* Create a github account if you do not already have one and request a repo owner to add you to the organization.
* If your workstation does not have GIT installed, then you'll want to install either Git or Github Desktop so you'll have the tools to clone the project repository.
---
### Driver Workstation setup steps

* These instructions are not yet written.
---
### RoboRio Update instructions

---
### 2023 Vision CoProcessor Setup and Development
<br>

#### Setup the raspberry pi sd card
* download the [raspberry pi imaging tool](https://www.raspberrypi.com/software/)
* Select custom image from the imaging tool
* Download the WPILibPi image from https://github.com/wpilibsuite/WPILibPi/releases and extract the .img file.  We're currently testing on version 2022.1.1.
* Select the WPILibPi image in the imaging tool and select the SD Card, then click WRITE.
* If not using the PI on a DHCP network, follow the instructions for setting the static IP.

<br>

#### Installing PhotonVision
* Download photonvision form the [releases page](https://github.com/PhotonVision/photonvision/releases). Be sure to check the versions are compatible with the WPILibPi release you're using.
* Using a web browser, navigate to the IP address of the raspberry Pi to open the web Interface.
* Click the "Writable" icon on the Pi Interface.
* Click the "Application" tab.
* Under "Vision Application Configuration" choose "Uploaded Jar File"
* Use the file upload control on the same panel to upload the PhotonVision jar file.
* A successful upload message will return "Application successfully uploaded! See the Vision Status tab for status and console output".
* Navigate to the IP Address of the Raspberry Pi, but use port 5800 (192.0.0.1:5800) to view the PhotonVision web interface.

### Configuring PhotonVision

<br>

#### Updating dynamic or static ip
* Static IP can be set by mounting the SD Card and updating dhcpcd.conf on the root of the disk.

<br>

#### Configuring device camera settings
* The BEST place to configure the camera is under the vision settings on (port 80) under the vision settings by clicking on the blue highlighted camera. <b>An incorrect setting here may cause issues with PhotonVision. </b>Make the system writable, update the setting, click save, mark the system as read-only, and then perform a restart.
* Device camera settings are stored in /boot/frc.json.  You can access this file when the SD Card is mounted on any device.
* Command to get list of devices on the pi:
```
v4l2-ctl --list-devices
```
* command to list supported resolutions on the camera:
```
v4l2-ctl -d /dev/video0 --list-formats-ext | more

```

#### Fixing Stuck Camera Configuration Bug
* There is an issue where at times the PhotonVision Dashboard shows no streams and the pipeline information is missing. When this occurs:<br>
1. Set the device to writable.
2. Next to the camera entry in the vision settings, click Remove camera.
3. Go to the vision settings and click "Add Conneted Camera"
4. Proceed to select the camera and add it with the resolution settings you'd like photonvision to use.


---
### Creating a backup of a Raspberry Pi SD Card on MS Windows:
* Download and install [Win32 Disk Imager](https://sourceforge.net/projects/win32diskimager/)
* Specify a file path for your backup .img file of the SD card and select the drive letter of the disk. 
* Click READ to perform the backup process.