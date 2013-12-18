                    _               _
      ___  _ __ ___| |__   ___  ___| |_ _ __ __ _
     / _ \| '__/ __| '_ \ / _ \/ __| __| '__/ _` |
    | (_) | | | (__| | | |  __/\__ \ |_| | | (_| |
     \___/|_|  \___|_| |_|\___||___/\__|_|  \__,_|


Instruments:

 * Cello
 * Oboe
 * Double bass

Percussion

 * Chinese cymbal
 * Chinese hand cymbal
 * Woodblock

## Installing samples

Samples from the philharmonic orchestra have to be downloaded and converted to wavs.

Make sure you have ffmpeg installed:

```bash
brew install ffmpeg
```

Run the following in a terminal to setup the files (as with any shell read it before running it).

```bash
mkdir -p ~/.overtone/orchestra/cello && cd ~/.overtone/orchestra &&
cd ~/.overtone/orchestra && wget --quiet --no-clobber http://www.philharmonia.co.uk/assets/audio/samples/cello/cello.zip &&
unzip -n -d cello cello.zip && cd cello && 
cd ~/.overtone/orchestra && wget --quiet --no-clobber  http://www.philharmonia.co.uk/assets/audio/samples/oboe/oboe.zip &&
unzip -n -d oboe oboe.zip && cd oboe && 
cd ~/.overtone/orchestra && wget --quiet --no-clobber http://www.philharmonia.co.uk/assets/audio/samples/double%20bass/double%20bass.zip &&
unzip -n -d "double-bass" "double bass.zip" && cd "double-bass" && 
cd ~/.overtone/orchestra && wget --quiet --no-clobber http://www.philharmonia.co.uk/assets/audio/samples/percussion/percussion.zip &&
unzip -n -d percussion percussion.zip && cd percussion && 
for f in cd ~/.overtone/orchestra/**/*.mp3; do ffmpeg -loglevel quiet -y -i "$f" "${f%.mp3}.wav"; done && 
rm ~/.overtone/orchestra/**/*.mp3

```

## Adding as a Dependency

Add to your project.clj file:

```clojure
[overtone.orchestra "0.1.0-SNAPSHOT"]
```

Samples provided by the  http://www.philharmonia.co.uk
