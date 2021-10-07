#import "LibFlutterCorePlugin.h"
#if __has_include(<lib_flutter_core/lib_flutter_core-Swift.h>)
#import <lib_flutter_core/lib_flutter_core-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "lib_flutter_core-Swift.h"
#endif

@implementation LibFlutterCorePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftLibFlutterCorePlugin registerWithRegistrar:registrar];
}
@end
