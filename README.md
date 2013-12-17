                    _               _             
      ___  _ __ ___| |__   ___  ___| |_ _ __ __ _ 
     / _ \| '__/ __| '_ \ / _ \/ __| __| '__/ _` |
    | (_) | | | (__| | | |  __/\__ \ |_| | | (_| |
     \___/|_|  \___|_| |_|\___||___/\__|_|  \__,_|


Intruments:

 * Cello
 * Oboe


## Installing samples

Samples from the philharmonia orchestra have to be downloaded and converted to wavs.

```bash
brew install ffmpeg
mkdir -p ~/.overtone/orchestra/cello && cd ~/.overtone/orchestra

wget http://www.philharmonia.co.uk/assets/audio/samples/cello/cello.zip
unzip -d cello cello.zip && cd cello
for f in *.mp3; do ffmpeg -i "$f" "${f%.mp3}.wav"; done

wget http://www.philharmonia.co.uk/assets/audio/samples/oboe/oboe.zip
unzip -d oboe oboe.zip && cd oboe
for f in *.mp3; do ffmpeg -i "$f" "${f%.mp3}.wav"; done
```

## Adding as a Dependency

Add to your project.clj file:

```clojure
[overtone.orchestra "0.1.0-SNAPSHOT"]
```

Samples provided by the  http://www.philharmonia.co.uk
