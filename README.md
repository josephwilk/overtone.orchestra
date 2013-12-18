                    _               _
      ___  _ __ ___| |__   ___  ___| |_ _ __ __ _
     / _ \| '__/ __| '_ \ / _ \/ __| __| '__/ _` |
    | (_) | | | (__| | | |  __/\__ \ |_| | | (_| |
     \___/|_|  \___|_| |_|\___||___/\__|_|  \__,_|


Instruments:

 * Cello
 * Oboe
 * Double bass
 * Violin

Percussion

 * Chinese cymbal
 * Chinese hand cymbal

## Installing samples

Samples from the philharmonia orchestra have to be downloaded and converted to wavs.

```bash
brew install ffmpeg
mkdir -p ~/.overtone/orchestra/cello && cd ~/.overtone/orchestra
cd ~/.overtone/orchestra/cello && cd ~/.overtone/orchestra

wget http://www.philharmonia.co.uk/assets/audio/samples/cello/cello.zip
unzip -d cello cello.zip && cd cello && for f in *.mp3; do ffmpeg -i "$f" "${f%.mp3}.wav"; done && rm *.mp3

wget http://www.philharmonia.co.uk/assets/audio/samples/oboe/oboe.zip
unzip -d oboe oboe.zip && cd oboe && for f in *.mp3; do ffmpeg -i "$f" "${f%.mp3}.wav"; done && rm *.mp3

wget http://www.philharmonia.co.uk/assets/audio/samples/double%20bass/double%20bass.zip
unzip -d "double-bass" "double bass.zip" && cd "double-bass" && for f in *.mp3; do ffmpeg -i "$f" "${f%.mp3}.wav"; done && rm *.mp3

wget http://www.philharmonia.co.uk/assets/audio/samples/violin/violin.zip
unzip -d violin violin.zip && cd violin && for f in *.mp3; do ffmpeg -i "$f" "${f%.mp3}.wav"; done && rm *.mp3

wget http://www.philharmonia.co.uk/assets/audio/samples/percussion/percussion.zip
unzip -d percussion percussion.zip && cd percussion && for f in *.mp3; do ffmpeg -i "$f" "${f%.mp3}.wav"; done && rm *.mp3
```

## Adding as a Dependency

Add to your project.clj file:

```clojure
[overtone.orchestra "0.1.0-SNAPSHOT"]
```

Samples provided by the  http://www.philharmonia.co.uk
