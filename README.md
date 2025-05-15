
# 🌍⚡ WorldEvents - Dynamic Events Plugin for Spigot

Welcome to **WorldEvents**, the all-in-one solution to spice up your Minecraft world with random, exciting, and fully customizable events!  
Perfect for community servers, streaming, or any world that needs ✨spontaneity✨.

---

## 🚀 Features

- **Dynamic Random Events:** Surprising world events occur every X minutes and last for a short time, affecting everyone’s gameplay!
- **Admin Controls:** Force any event, anytime, with an easy command.
- **Fully Configurable:** Toggle events, change timings, and control schedule and permissions.
- **Translatable:** All messages in their own easy-to-edit file!
- **Vault Support:** Full compatibility with Vault permissions.
- **Easy to Extend:** Modular design—add your own events!
- **Works with:** Minecraft Java 1.21+, Java 17+.

---

## 🎉 Included Events

| Event              | Description                                                                           |
|--------------------|---------------------------------------------------------------------------------------|
| ⚡ Magic Storm      | Lightning everywhere, constant rain, mobs become stronger and glow!                   |
| 🌑 Eternal Night   | Day/night cycle locks to night, and rare mobs start appearing!                        |
| 🎁 Loot Rain       | Loot chests drop from the sky at random locations!                                    |
| 🏆 Treasure Hunt   | A special chest spawns somewhere in the world and its coordinates are announced!      |

---

## 🛠️ How to Install

1. **Download/Build the plugin:**
   - Use Maven:  
     ```bash
     mvn clean package
     ```
   - Or download from [releases](#) (if available).

2. **Put the generated `WorldEvent-xxx.jar` in your server’s `/plugins` directory.**

3. **Start your server.** The plugin creates its config files and is ready to go!

---

## ⚙️ Configuration

All main config files are located in `/plugins/WorldEvent/`:

### `config.yml`
```yaml
event-interval-minutes: 10      # Time between events (minutes)
event-duration-minutes: 3       # Each event duration (minutes)
enabled-events:
  magic_storm: true
  eternal_night: true
  loot_rain: true
  treasure_hunt: true
```

### `messages.yml`
All in-game messages and announcements.  
_Translate, customize, decorate with color codes!_  
Example:
```yaml
event_start: "{prefix} &aEvent started: &e{event}"
event_end: "{prefix} &cThe event &e{event} &chas ended!"
...
```

---

## 🕹️ Commands

| Command                                  | Description                          | Permission         |
|-------------------------------------------|--------------------------------------|--------------------|
| `/worldevents start <event>`              | Force-starts a specific event        | `worldevents.admin` |

If no event is running, a random enabled event will start on a timer.

---

## 🧑‍💼 Permissions

| Node                | Default | Description                                  |
|---------------------|---------|----------------------------------------------|
| `worldevents.admin` | OP      | Can force-start events and manage the plugin |

---

## 🤖 Dependencies

- [Spigot API 1.21+](https://www.spigotmc.org/)
- [Vault (for permissions)](https://www.spigotmc.org/resources/vault.34315/)

---

## 📦 Project Structure

```
WorldEvent/
├── src/
│   └── main/
│       ├── java/com/raffymimi/worldEvent/
│       │   ├── event/         # All event classes (one for each event)
│       │   ├── command/       # Command logic
│       │   ├── manager/       # Event scheduler & logic
│       │   ├── util/          # Utilities (messages, Vault permissions)
│       │   └── WorldEvent.java# Main plugin file
│       └── resources/
│           ├── plugin.yml
│           ├── config.yml
│           └── messages.yml
├── pom.xml
```

---

## 🧑‍💻 For Developers

- **Add new event classes** in `/event/`!  
  Just extend `BaseEvent` and register your class in the `EventManager`.
- **Messages**: Use placeholders like `{event}`, `{prefix}`, `{coords}` in `messages.yml`.
- **Vault** is used for permissions.

---

## 🙏 Credits

**Plugin by:** [Raffymimii](https://github.com/raffymimii)  
**Portfolio:** https://portfolio.raffymimi.it/

Inspired by the beautiful chaos of Minecraft events!

_Contributions, bug reports, and suggestions welcome!_

---

## ❤️ Support

If you enjoy this plugin, star ⭐ the repo or buy me a [coffee](https://paypal.me/raffymimi?country.x=IT&locale.x=it_IT)!

---

#### Enjoy making your Minecraft world unpredictable and fun!  
*Let the chaos begin!* 🌩️🎉
