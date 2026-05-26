import os

def replace_in_file(filepath):
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
    except UnicodeDecodeError:
        return # Skip binary files
    
    # Text replacements
    # 1. Package name
    new_content = content.replace('com.maxrave.simpmusic', 'com.pandoos.music')
    # 2. Case-sensitive app name
    new_content = new_content.replace('SimpMusic', 'Pandoos')
    # 3. Case-insensitive app name: replace lowercase
    new_content = new_content.replace('simpmusic', 'pandoos')

    if new_content != content:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(new_content)
        print(f"Updated: {filepath}")

def rename_directories(root_dir):
    # We want to replace 'simpmusic' in paths with 'pandoos'
    # Specifically for com/maxrave/simpmusic -> com/pandoos/music
    # This might require multiple passes
    for dirpath, dirnames, filenames in os.walk(root_dir, topdown=False):
        for dirname in dirnames:
            if dirname == 'simpmusic':
                parent = os.path.basename(dirpath)
                if parent == 'maxrave':
                    # We have .../com/maxrave/simpmusic
                    # Rename simpmusic to music
                    old_path = os.path.join(dirpath, dirname)
                    new_path = os.path.join(dirpath, 'music')
                    os.rename(old_path, new_path)
                    print(f"Renamed dir: {old_path} -> {new_path}")
                    
                    # Also rename maxrave to pandoos
                    old_parent_path = dirpath
                    new_parent_path = os.path.join(os.path.dirname(dirpath), 'pandoos')
                    os.rename(old_parent_path, new_parent_path)
                    print(f"Renamed dir: {old_parent_path} -> {new_parent_path}")

def process_all_files(root_dir):
    for dirpath, _, filenames in os.walk(root_dir):
        if '.git' in dirpath:
            continue
        for filename in filenames:
            if filename in ['rename_app.py']:
                continue
            filepath = os.path.join(dirpath, filename)
            replace_in_file(filepath)

if __name__ == "__main__":
    root = '.'
    print("Replacing text in files...")
    process_all_files(root)
    print("Renaming directories...")
    rename_directories(root)
    print("Done!")
