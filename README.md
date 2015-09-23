# AndroidFileReadWrite
文件读写操作

## Import
[JitPack](https://jitpack.io/)

Add it in your project's build.gradle at the end of repositories:

```gradle
repositories {
  // ...
  maven { url "https://jitpack.io" }
}
```

Step 2. Add the dependency in the form

```gradle
dependencies {
  compile 'com.github.vilyever:AndroidFileReadWrite:1.0.1'
}
```

## Usage
```java

VDContextHolder.initial(getApplicationContext()); // initial context holder

String text = "hello text";
String filePath = VDFileConstant.getCacheDir("test").getAbsolutePath() + "/text";
VDFileWriter.writeText(new File(filePath), text);

Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
filePath = VDFileConstant.getCacheDir("test").getAbsolutePath() + "/launcher.png";
VDFileWriter.writeBitmap(new File(filePath), bitmap);
bitmap.recycle();

TextView label = (TextView) findViewById(R.id.label);
filePath = VDFileConstant.getCacheDir("test").getAbsolutePath() + "/text";
label.setText(VDFileReader.readText(new File(filePath)));

ImageView imageView = (ImageView) findViewById(R.id.imageView);
filePath = VDFileConstant.getCacheDir("test").getAbsolutePath() + "/launcher.png";
bitmap = VDFileReader.readBitmap(new File(filePath));
imageView.setImageBitmap(bitmap);

```

## License
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

