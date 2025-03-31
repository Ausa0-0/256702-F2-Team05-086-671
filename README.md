# 256702-F2-Team05-086-671
Java Project
แบบข้อเสนอโครงาน
ชื่อโครงงาน
ระบบการติดตามการใช่จ่ายส่วนตัวพร้อมระบบแจ้งเตือน (Personal expense  tracking system with reminders)
สมาชิก
1.	จิรัศยา ไทยถาวร  6730300086
2.	อุษา อุปายโกศล  6730300671
รายละเอียดโดยย่อ
โครงการนี้จะพัฒนาระบบเพื่อช่วยผู้ใช้ในการติดตามและบันทึกข้อมูลการใช้จ่ายประจำวัน รายสัปดาห์ หรือรายเดือน ระบบจะช่วยให้ผู้ใช้สามารถจัดการข้อมูลการใช้จ่ายได้ เช่น การเพิ่ม แก้ไข หรือการลบข้อมูล นอกจากนี้ยังมีฟังก์ชันการแสดงรายงานในรูปแบบกราฟ เพื่อให้ผู้ใช้สามารถวิเคราะห์แนวโน้มการใช้จ่ายของตนเองได้ อีกทั้งยังมีระบบแจ้งเตือนเมื่อยอดเงินที่ใช้มากเกินกว่ากำหนดที่ตัวผู้ใช้งานตั้งไว้ 
คุณลักษณะและขอบเขต
คุณลักษณะขั้นต่ำ
    •	ฟังก์ชันการบันทึกและจัดการข้อมูลการใช้จ่าย (เพิ่ม แก้ไข ลบรายการ)
    •	หน้าจอหลักที่แสดงรายการการใช้จ่ายทั้งหมด
    •	ฟังก์ชันแสดงรายงานการใช้จ่ายในรูปแบบกราฟ เช่น กราฟวงกลมหรือกราฟเส้น
คุณลักษณะเพิ่มเติม
    •	ฟังก์ชันการกรองข้อมูลการใช้จ่าย เช่น กรองตามหมวดหมู่ หรือช่วงเวลา
    •	ฟังก์ชันการแจ้งเตือนเมื่อการใช้จ่ายเกินงบประมาณที่กำหนด
    •	รองรับการแสดงผลบนอุปกรณ์มือถือหรือแท็บเล็ต


แผนการดำเนินโครงงาน
ระยะ	                                                        ช่วงเวลา                                        	ผลลัพธ์ที่คาดหวัง
การเตรียมโครงงาน	                                            สัปดาห์ที่ 1	                                    -โครงสร้างพื้นฐานโปรเจคพร้อมใช้งาน
                                                                                                            -สร้าง GitHub repository
                                                                                                            -ข้อเสนอโครงงานเสร็จสมบูรณ์และยืนยันขอบเขตงาน

การเตรียมโค้ดเบื้องต้น	                                        สัปดาห์ที่ 2	                                    -โค้ดต้นแบบสำหรับการเพิ่มรายการการใช้จ่ายสำเร็จ
                                                                                                            -UI เบื้องต้นพร้อมสำหรับทดสอบการป้อนข้อมูล

พัฒนาฟังก์ชันหลัก	                                            สัปดาห์ที่ 3	                                     -ฟังก์ชันการเพิ่ม แก้ไข และลบรายการใช้งานได้อย่างถูกต้อง
                                                                                                             -หน้าจอหลักสามารถแสดงผลข้อมูลการใช้จ่ายที่เพิ่มเข้ามาได้

ทดสอบฟีเจอร์พื้นฐาน	                                        สัปดาห์ที่ 4	                                      -ฟังก์ชันพื้นฐานทั้งหมดทำงานได้ตามที่คาดหวัง

เพิ่มฟีเจอร์แสดงผลข้อมูล	                                        สัปดาห์ที่ 5	                                       -UI ปรับปรุงและดูน่าสนใจขึ้นเพื่อรองรับการแสดงผลข้อมูลอย่างชัดเจน

ทดสอบระบบและแก้ไขบั๊ก	                                        สัปดาห์ที่ 6	                                        -ระบบทำงานได้อย่างเสถียรและถูกต้อง ไม่มีบั๊กสำคัญ
                                                                                                                -ระบบสามารถรองรับการเพิ่มข้อมูลจำนวนมากได้

ปรับแต่งและเตรียมเอกสสาร	                                    สัปดาห์ที่ 7	                                        -UI พร้อมสำหรับการใช้งานจริง
                                                                                                                -คู่มือผู้ใช้และเอกสารประกอบเสร็จสมบูรณ์

พร้อมส่งมอบงานและนำเสนอ	                                    สัปดาห์ที่ 8	                                         -ระบบทำงานได้อย่างสมบูรณ์พร้อมส่งมอบ
                                                                                                                 -พร้อมสำหรับการนำเสนอโครงการและสาธิตการใช้งาน


การแบ่งงาน
สมาชิกคนที่ 1: ออกแบบ UI และพัฒนาโค้ดที่เกี่ยวข้องกับส่วนติดต่อผู้ใช้ (front-end) เช่น การแสดงรายการการใช้จ่าย 
สมาชิกคนที่ 2: พัฒนาโค้ดที่เกี่ยวข้องกับการจัดการข้อมูล (back-end) เช่น การจัดเก็บและเรียกใช้ข้อมูลฟังก์ชันการกรองข้อมูล ทดสอบระบบ และจัดทำเอกสารโครงการ

ความท้าทายและความเสี่ยง
    •	การจัดการข้อมูลการใช้จ่ายที่ซับซ้อนและการสร้างรายงานอาจต้องใช้เวลาศึกษาเพิ่มเติม
    •	การออกแบบ UI ให้เข้าใจง่ายและสะดวกต่อการใช้งาน
    •	การจัดการเวลาในการพัฒนาฟังก์ชันให้ครบถ้วน
ต้นแบบและเอกสารอ้างอิง
JavaFX เป็นเครื่องมือหลักที่ใช้สร้าง UI สำหรับแอปพลิเคชัน
    •	JavaFX Official Documentation (เอกสารทางการ) https://openjfx.io/
    •	JavaFX Scene Builder (GUI Designer - ออกแบบ UI แบบ Drag & Drop) https://gluonhq.com/products/scene-builder/
การจัดการฐานข้อมูล (SQLite/MySQL & JDBC)ใช้เก็บข้อมูลรายรับ-รายจ่าย และการวิเคราะห์ค่าใช้จ่าย
    •	SQLite https://www.sqlitetutorial.net/
    •	การใช้ SQLite กับ Java (Java SQLite Tutorial) https://www.sqlitetutorial.net/sqlite-java/
    •	MySQL กับ Java (เชื่อมต่อด้วย JDBC) https://dev.mysql.com/doc/connector-j/en/
    •	Java JDBC (Java Database Connectivity - สำหรับเชื่อมฐานข้อมูล) https://www.javatpoint.com/java-jdbc

การจัดการไฟล์ CSV/JSON
    •	รองรับการ Export/Import ข้อมูล OpenCSV (ไลบรารีสำหรับอ่าน/เขียนไฟล์ CSV ใน Java https://www.baeldung.com/opencsv
    •	Apache Commons CSV (อีกทางเลือกสำหรับจัดการไฟล์ CSV) https://commons.apache.org/proper/commons-csv/
ระบบแจ้งเตือนผู้ใช้เมื่อใช้จ่ายเกินงบประมาณ
    •	JavaFX Alert (สร้าง Popup แจ้งเตือนใน JavaFX) https://code.makery.ch/blog/javafx-dialogs-official/
    •	Java TimerTask (สร้างการแจ้งเตือนอัตโนมัติเป็นระยะๆ) https://www.baeldung.com/java-timer-and-timertask


