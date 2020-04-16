## EMF-Detector
Android Electro Magnetic Field Detector Library

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Sdk](https://img.shields.io/badge/sdk-16%2B-brightgreen.svg?style=plastic)](https://android-arsenal.com/api?level=16)



How To
-----------------
How does another developer add this as a dependency?

STEP 1:  Add Below lines into project-level build.gradle:    

        allprojects {
          repositories {
            // ...
            maven { url 'https://jitpack.io' }

          }
        }
        
STEP 2: Reference the library itself in your module-level build.gradle:      

        implementation "com.github.Yabaze:EMF-Detector:V1.0.0"

STEP 3: Must declare this Variable 

  ``Kotlin``

    val emfDetector = object : EmfDetector() {

        override fun returnEmfValue(status: EmfStatus, emfValue: Float, unit: EmfUnit) {
        
            when(status){
                EmfStatus.SUCCESS->{
                    //emf detected successfully
                }
                EmfStatus.FAILURE->{
                    //Failed To Detect EMF Value
                }
                EmfStatus.NOT_AVAILABLE->{
                   //Magnetic Sensor Not Available in your Device
                }
            }

        }

    }
 
 ``Java``
 
     EmfDetector emfDetector = new EmfDetector() {
        @Override
        public void returnEmfValue(@NotNull EmfStatus emfStatus, float v, @NotNull EmfUnit emfUnit) {
            switch (emfStatus){
                case SUCCESS:
                    //emf detected successfully
                    break;
                
                case FAILURE:
                    //Failed To Detect EMF Value
                    break;
                
                case NOT_AVAILABLE:
                    //Magnetic Sensor Not Available in your Device
                    break;
            }
        }
    };



STEP 4: Get Emf Value 

        emfDetector.checkForSensor(context)
                       
                       
## NOTE:
1. Emf Value return in Micro tesla
                      
## Author

👤 **Yabaze**

- FaceBook: [@MirakleYabaze](https://www.facebook.com/mirakle.yabaze)
- twitter: [@MirakleYabaze](https://twitter.com/mirakleyabaze)
- instagram: [@Yabaze1](https://www.instagram.com/yabaze1/)

License
-----------------

      Copyright (c) 2020-present, yabaze.t

      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

      Unless required by applicable law or agreed to in writing,
      software distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.
