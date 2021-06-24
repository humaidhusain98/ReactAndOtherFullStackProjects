import Image from 'next/image';
import HeaderItem from './HeaderItem';
import { BadgeCheckIcon, BeakerIcon, CollectionIcon, HomeIcon, LightningBoltIcon, SearchIcon, UserIcon } from '@heroicons/react/outline'

const Header = () => {
    return ( 
    <header className="flex flex-col sm:flex-row m-5 justify-between items-center">
        <div className="flex flex-grow justify-evenly max-w-2xl h-auto">
            <HeaderItem title="HOME" Icon={HomeIcon}/>
            <HeaderItem title="TRENDING" Icon={LightningBoltIcon}/>
            <HeaderItem title="VERIFIED" Icon={BadgeCheckIcon}/>
            <HeaderItem title="COLLECTIONS" Icon={CollectionIcon}/>
            <HeaderItem title="SEARCH" Icon={SearchIcon}/>
            <HeaderItem title="ACCOUNT" Icon={UserIcon}/>
        </div>
        <Image
            className="object-contain"
            src="https://links.papareact.com/ua6"
            width={100}
            height={100}
        />
    </header>
    
     );
}
 
export default Header;