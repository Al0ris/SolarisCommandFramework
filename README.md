# SolarisCommandFramework

A small command library for the Solaris server network, built on top of [Cloud Command Framework v2](https://cloud.incendo.org/). It centralizes command registration, exception handling, and player-only command enforcement so plugins don't have to hand-roll that boilerplate per command.

## Features

- **Centralized exception handling** — syntax errors, permission failures, invalid arguments, and internal exceptions are all routed through one handler instead of scattered try/catch blocks per command.
- **Player-only command support** — wrap a handler with `PlayerCommands.playerOnly(...)` instead of manually checking `instanceof Player` in every command.
- **Simple static entry point** — `SolarisCommandFramework.init()` once in `onEnable`, then `registerCommand()` per command.
- **DI-agnostic** — no dependency injection framework required. Works standalone in any Paper plugin.

## Installation

Add [JitPack](https://jitpack.io) as a repository:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Al0ris:SolarisCommandFramework:v0.1.0'
}
```

Replace `v0.1.0` with the latest tagged release.

## Usage

### 1. Initialize in `onEnable`

```java
@Override
public void onEnable() {
    CommandManager<CommandSourceStack> manager = /* your PaperCommandManager setup */;
    SolarisCommandFramework.init(this, manager);
}
```

This registers the centralized exception handler and prepares the framework to accept command registrations.

### 2. Implement `SolarisCommand`

```java
public class IslandCommands implements SolarisCommand {

    @Override
    public void register(CommandManager<CommandSourceStack> manager) {
        manager.command(
            manager.commandBuilder("island")
                .literal("create")
                .required("name", StringParser.stringParser())
                .handler(PlayerCommands.playerOnly((player, ctx) -> {
                    String name = ctx.get("name");
                    // create island for player
                }))
        );
    }
}
```

`PlayerCommands.playerOnly(...)` ensures the handler only runs for player senders. If a non-player (e.g. console) tries to run the command, a `NotPlayerException` is thrown and handled automatically with a standard error message.

### 3. Register the command

```java
SolarisCommandFramework.registerCommand(new IslandCommands());
```

Register as many commands as needed — one call per command. If your plugin has many commands, loop over a list; if it has a few, call `registerCommand` individually. The framework doesn't impose a pattern here.

## Exception Handling

The following exceptions are handled automatically once `init()` is called:

| Exception | Result |
|---|---|
| `NotPlayerException` | "This command can only be used by players." |
| `NoPermissionException` | "You don't have permission to do that." |
| `InvalidSyntaxException` | "Usage: /\<correct syntax\>" |
| `ArgumentParseException` | "Invalid argument: \<cause\>" |
| `CommandExecutionException` | "An internal error occurred." (stack trace logged) |

## Requirements

- Java 25
- Paper API 26.2+
- Cloud Command Framework v2 (`cloud-core`, `cloud-paper`)

## License

[MIT](LICENSE)
