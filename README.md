# MusicGenerator
Generates music using Java's Sound/MIDI API

MusicGenerator is simple and does not have a UI. Currently, the output consists of a piano playing chords and a Synth Drum playing randomized drum patterns.  
There are several standard chord types embedded into the ChordType class, and there is an extra 'RANDOM' chord type which generates a chord without following any specific guidelines.  
All chords are guaranteed not to be dissonant, as the smallest interval allowed is a minor third.

Currently, chord progressions have no explicit pattern. They are only a collection of randomized chords.
