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
  compile 'com.github.vilyever:AndroidFileReadWrite:1.1.0'
}
```

## Usage
```java

String text = "hello text";
String filePath = FileConstant.getCacheDir("test").getAbsolutePath() + "/text";
FileWriter.writeText(new File(filePath), text);

Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
filePath = FileConstant.getCacheDir("test").getAbsolutePath() + "/launcher.png";
FileWriter.writeBitmap(new File(filePath), bitmap);
bitmap.recycle();

TextView label = (TextView) findViewById(R.id.label);
filePath = FileConstant.getCacheDir("test").getAbsolutePath() + "/text";
label.setText(FileReader.readText(new File(filePath)));

ImageView imageView = (ImageView) findViewById(R.id.imageView);
filePath = FileConstant.getCacheDir("test").getAbsolutePath() + "/launcher.png";
bitmap = FileReader.readBitmap(new File(filePath));
imageView.setImageBitmap(bitmap);

```

## License
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

