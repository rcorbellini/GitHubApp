import 'dart:async';

import 'package:flutter/services.dart';

class LibFlutterCore {
  static const MethodChannel _channel = const MethodChannel('lib_flutter_core');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String?> navigateNewScreen(
      String packageName, String className) async {
    final String? version = await _channel.invokeMethod('navigateNewScreen',
        {"packageName": packageName, "className": className});
    return version;
  }
}
