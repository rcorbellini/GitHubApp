import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:lib_flutter_core/lib_flutter_core.dart';

void main() {
  const MethodChannel channel = MethodChannel('lib_flutter_core');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await LibFlutterCore.platformVersion, '42');
  });
}
