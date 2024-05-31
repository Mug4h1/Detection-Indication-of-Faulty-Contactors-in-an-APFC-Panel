# Detection-Indication-of-Faulty-Contactors-in-an-APFC-Panel
<p>
    This project is about detecting and indicating faulty contactors in an APFC (Automatic Power Factor Controllers) Panel.
</p>
<h3>About</h3>
<p>
    As per government rules, if any factory is consuming more than 40kW of power then, they have to maintain the power factor to unity. Otherwise, the factory will end up consuimg excessive power and will be charged a heavy penalty fine.<br>
    For this reason, the APFC panel is used which maintains the value of power factor to around unity and ensures that excess power is not consumed. The changes in PF may occur due to failure in components of the APFC panel such as contactors and capacitors.
</p>
<h3>Problem statement</h3>
<p>
    After a long run, the contactors may get damaged, due to which the power consumption of the factory increases. It ultimately results in heavy penalty bills imposed by the government. After the bill is received the factory will connect with a professional to checkout their panel. This manual labor will take up a lot of time as the professional has to checkout/test each and every component of the panel, which ultimately results in more power consumption in that period as well as increased penalty.
</p>
<h3>Proposed Solution</h3>
For this issue, our proposed solution is to detect and indicate the faultiness of a contactor via an app and on-site LCD.<br>
This project is divided into 2 parts:
<ul>
    <li>Hardware</li>
    <li>Software</li>
</ul>
<p>
    1. These panels usually operate on 440V AC electricity and have more than 2 contactors. For easy demonstration and convenience, we're using a panel that operates on 240V AC electricity and 2 contactors.<br>
    In order to display the status of both the contactors on a LCD screen, we used the Arduino board to connect the LCD as well as get input from the panel. The challenge was to step-down the 240V AC to 5V DC (max voltage to operate Arduino board).
</p>
<p>
    2. From software perspective, we needed to program 3 things.<br>First, receive input from the panel and process the data. Then display the results on the LCD as well as send the results to nodemcu for further processes. Second, process the results and send the data to a url where the Android application can utilize it. Finally, create an Android application which displays the status of the contactors in a sophisticated and readable way.
</p>
<p>
    This repository contains the software aspect of our project.<br>
    For more infomation, you can checkout our published paper - <a href="https://www.ijnrd.org/viewpaperforall.php?paper=IJNRD2305184">Detection and Indication of Faulty Contactors in an APFC Panel</a>
</p>