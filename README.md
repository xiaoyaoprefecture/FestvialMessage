# FestvialMessage
这是节日短信的小程序，在这分代码里主要使用的技术点有：
一：TabLayout+Fragment+ViewPager显示主界面
二：节日短信碎片是用的RecyclerView的GridView来实现的，发送记录碎片是用Listview来实现的
三：点击每一个节日短信跳转到的短信备选界面是用listview实现的，这里面还使用了FloatingActionBar作为发送短信的触发控件，
点击添加联系人使用的是
                Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                activity.startActivityForResult(intent,contacts);
   然后在Activity里面重写onActivityResult（）去获取选中的联系人，点击发送按钮后使用ProgressDialog（告诉用户正在发送）、BroadCastReceiver（一个监听短信发送成功，一个监听短信接收成功）和Toast来告诉用户该短信是否发送成功
四：发送记录碎片内部数据是存储到数据库（使用的是继承SqliteOpenHelper）中的
五：其中的数据传递：从适配器里传送数据到承载的Activity使用的是自定义内部接口；从Activity到另一个Activity中的碎片使用的是Loader机制、Sqlite和ontentProvider来传递的数据
    
    整个小程序使用到的权限有：
    <uses-permission android:name="android.permission.READ_CONTACTS"/>
    <uses-permission android:name="android.permission.WRITE_CONTACTS"/>
    <uses-permission android:name="android.permission.SEND_SMS"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    
    这个小程序还存在的bug：
    短信备选界面中的listview的item内，不管点击哪个item的添加联系人，其它的也会同时添加进去（没有写item的点击事件），并且发送的短信始终是第一个item的内容（可能是因为使用的同一个适配器）
