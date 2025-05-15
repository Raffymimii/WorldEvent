
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
   - Or download from [releases](https://github.com/Raffymimii/WorldEvent/releases/tag/Plugin).

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




---
---
---
---
---

## 🇮🇹 Descrizione in Italiano

Benvenuto in **WorldEvents**, la soluzione tutto-in-uno per dare una marcia in più al tuo mondo Minecraft con eventi casuali, emozionanti e completamente personalizzabili!
Perfetto per server community, streaming o qualsiasi mondo che ha bisogno di un pizzico di ✨spontaneità✨.

---

## 🚀 Funzionalità

* **Eventi Casuali Dinamici:** Eventi sorprendenti si attivano ogni X minuti e durano per poco tempo, influenzando il gameplay di tutti!
* **Controlli Admin:** Forza qualsiasi evento in qualsiasi momento con un semplice comando.
* **Completamente Configurabile:** Attiva/disattiva eventi, modifica la frequenza e gestisci permessi e pianificazione.
* **Traducibile:** Tutti i messaggi sono in un file dedicato, facile da modificare!
* **Supporto Vault:** Compatibile con i permessi tramite Vault.
* **Facile da Estendere:** Struttura modulare—puoi aggiungere i tuoi eventi personalizzati!
* **Compatibile con:** Minecraft Java 1.21+, Java 17+.

---

## 🎉 Eventi Inclusi

| Evento              | Descrizione                                                                              |
| ------------------- | ---------------------------------------------------------------------------------------- |
| ⚡ Tempesta Magica   | Fulmini ovunque, pioggia costante, i mob diventano più forti e brillano!                 |
| 🌑 Notte Eterna     | Il ciclo giorno/notte si blocca sulla notte, e compaiono mob rari!                       |
| 🎁 Pioggia di Loot  | Casse piene di loot cadono dal cielo in punti casuali del mondo!                         |
| 🏆 Caccia al Tesoro | Una cassa speciale appare da qualche parte nel mondo e le coordinate vengono annunciate! |

---

## 🛠️ Come Installare

1. **Scarica o compila il plugin:**

   * Usa Maven:

     ```bash
     mvn clean package
     ```
   * Oppure scarica da [releases](https://github.com/Raffymimii/WorldEvent/releases/tag/Plugin)

2. **Inserisci il file `WorldEvent-xxx.jar` nella cartella `/plugins` del tuo server.**

3. **Avvia il server.** Il plugin genererà i file di configurazione automaticamente!

---

## ⚙️ Configurazione

Tutti i file si trovano in `/plugins/WorldEvent/`:

### `config.yml`

```yaml
event-interval-minutes: 10      # Minuti tra un evento e l'altro
event-duration-minutes: 3       # Durata di ogni evento (in minuti)
enabled-events:
  magic_storm: true
  eternal_night: true
  loot_rain: true
  treasure_hunt: true
```

### `messages.yml`

Tutti i messaggi del gioco.
*Puoi tradurli, personalizzarli, colorarli come vuoi!*
Esempio:

```yaml
event_start: "{prefix} &aEvento iniziato: &e{event}"
event_end: "{prefix} &cL'evento &e{event} &cè terminato!"
...
```

---

## 🕹️ Comandi

| Comando                      | Descrizione                 | Permesso            |
| ---------------------------- | --------------------------- | ------------------- |
| `/worldevents start <event>` | Avvia manualmente un evento | `worldevents.admin` |

Se nessun evento è in corso, ne inizierà uno casuale tra quelli abilitati.

---

## 🧑‍💼 Permessi

| Nodo                | Default | Descrizione                            |
| ------------------- | ------- | -------------------------------------- |
| `worldevents.admin` | OP      | Può avviare eventi e gestire il plugin |

---

## 🤖 Dipendenze

* [Spigot API 1.21+](https://www.spigotmc.org/)
* [Vault (per i permessi)](https://www.spigotmc.org/resources/vault.34315/)

---

## ❤️ Supporto

Se ti piace il plugin, lascia una ⭐ al repo o offrimi un [caffè](https://paypal.me/raffymimi?country.x=IT&locale.x=it_IT)!

---

#### Divertiti a rendere il tuo mondo Minecraft imprevedibile e divertente!

*Che il caos abbia inizio!* 🌩️🎉

